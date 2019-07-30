package ru.yaal.contexthabit.ui.habit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static ru.yaal.contexthabit.repo.room.action.ActionEntity.ActionType.NEGATIVE;
import static ru.yaal.contexthabit.repo.room.action.ActionEntity.ActionType.POSITIVE;
import static ru.yaal.contexthabit.ui.context.ContextActivity.repository;

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

        Button negativeMinusButton = habitView.getNegativeMinusButton();
        Button negativePlusButton = habitView.getNegativePlusButton();
        Button positiveMinusButton = habitView.getPositiveMinusButton();
        Button positivePlusButton = habitView.getPositivePlusButton();

        TextView negativeValueTextView = habitView.getNegativeValueTextView();
        TextView positiveValueTextView = habitView.getPositiveValueTextView();

        model.negativeCount.observe(habitActivity,
                new IntegerObserver(negativeValueTextView, negativeMinusButton));
        model.positiveCount.observe(habitActivity,
                new IntegerObserver(positiveValueTextView, positiveMinusButton));

        HabitEntity habit = habits.get(position);
        model.contextEntity.setValue(context);
        model.habitEntity.setValue(habit);
        model.negativeCount.setValue(repository.getNegativeValue(habit.id));
        model.positiveCount.setValue(repository.getPositiveValue(habit.id));

        addButtonListener(negativeMinusButton, model, -1, NEGATIVE);
        addButtonListener(negativePlusButton, model, 1, NEGATIVE);
        addButtonListener(positiveMinusButton, model, -1, POSITIVE);
        addButtonListener(positivePlusButton, model, 1, POSITIVE);
    }

    private void addButtonListener(Button button, HabitViewModel model, int valueChange,
                                   ActionEntity.ActionType type) {
        ButtonOnClickListener listener = new ButtonOnClickListener(model, valueChange, type);
        button.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    private static class ButtonOnClickListener implements View.OnClickListener {
        private final HabitViewModel model;
        private final int valueChange;
        private final ActionEntity.ActionType type;

        ButtonOnClickListener(HabitViewModel model, int valueChange, ActionEntity.ActionType type) {
            this.model = model;
            this.valueChange = valueChange;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            ActionEntity actionEntity = new ActionEntity();
            ContextEntity contextEntity = model.contextEntity.getValue();
            actionEntity.contextId = contextEntity != null ? contextEntity.id : null;
            HabitEntity habitEntity = model.habitEntity.getValue();
            actionEntity.habitId = habitEntity != null ? habitEntity.id : null;
            actionEntity.date = LocalDateTime.now();
            actionEntity.valueChange = valueChange;
            actionEntity.type = type;

            repository.saveAction(actionEntity);

            switch (type) {
                case POSITIVE:
                    updateValue(model.positiveCount, valueChange);
                    break;
                case NEGATIVE:
                    updateValue(model.negativeCount, valueChange);
                    break;
                default:
                    throw new RuntimeException("Unknown ActionType: " + type);
            }
        }

        private void updateValue(MutableLiveData<Integer> liveData, int valueChange) {
            Integer value = liveData.getValue();
            value = value != null ? value : 0;
            int newValue = value + valueChange;
            liveData.setValue(newValue);
        }
    }
}
