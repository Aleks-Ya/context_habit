package ru.yaal.contexthabit.ui.habit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.ui.context.ContextActivity;

public class HabitViewAdapter extends RecyclerView.Adapter<HabitViewAdapter.HabitViewHolder> {
    private ContextEntity context;
    private List<HabitEntity> habits;

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        HabitViewHolder(View view) {
            super(view);
        }
    }

    HabitViewAdapter(ContextEntity context, List<HabitEntity> habits) {
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflated = inflater.inflate(R.layout.view_habit, parent, false);
        return new HabitViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        HabitView habitView = (HabitView) holder.itemView;
        HabitActivity habitActivity = (HabitActivity) habitView.getContext();

        HabitViewModel model = new HabitViewModel();

        TextView habitNameTextView = habitView.getHabitNameTextView();
        HabitEntityObserver habitEntityObserver = new HabitEntityObserver(habitNameTextView);
        model.habitEntity.observe(habitActivity, habitEntityObserver);

        TextView negativeValueTextView = habitView.getNegativeValueTextView();
        IntegerObserver negativeValueObserver = new IntegerObserver(negativeValueTextView);
        model.negativeCount.observe(habitActivity, negativeValueObserver);


        HabitEntity habit = habits.get(position);

        model.contextEntity.setValue(context);
        model.habitEntity.setValue(habit);
        int negativeCount = ContextActivity.repository.getNegativeValue(context.id, habit.id);
        model.negativeCount.setValue(negativeCount);
        model.positiveCount.setValue(0);

        NegativeMinusButtonOnClickListener negativeMinusButtonOnClickListener =
                new NegativeMinusButtonOnClickListener(model);

        habitView.getNegativeMinusButton()
                .setOnClickListener(negativeMinusButtonOnClickListener);

    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class NegativeMinusButtonOnClickListener implements View.OnClickListener {
        private final HabitViewModel model;

        NegativeMinusButtonOnClickListener(HabitViewModel model) {
            this.model = model;
        }

        @Override
        public void onClick(View view) {
            ActionEntity actionEntity = new ActionEntity();
            ContextEntity contextEntity = model.contextEntity.getValue();
            actionEntity.contextId = contextEntity != null ? contextEntity.id : null;
            HabitEntity habitEntity = model.habitEntity.getValue();
            actionEntity.habitId = habitEntity != null ? habitEntity.id : null;
            actionEntity.date = LocalDateTime.now();
            actionEntity.valueChange = -1;
            actionEntity.type = ActionEntity.ActionType.NEGATIVE;

            ContextActivity.repository.saveAction(actionEntity);

            Integer negativeCountValue = model.negativeCount.getValue();
            negativeCountValue = negativeCountValue != null ? negativeCountValue : 0;
            int newValue = negativeCountValue + actionEntity.valueChange;
            model.negativeCount.setValue(newValue);
        }
    }
}
