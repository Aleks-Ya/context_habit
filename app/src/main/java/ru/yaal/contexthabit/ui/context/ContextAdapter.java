package ru.yaal.contexthabit.ui.context;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.ui.habit.HabitActivity;
import ru.yaal.contexthabit.ui.habit.HabitList;

import static ru.yaal.contexthabit.ui.context.ContextActivity.CONTEXT_EXTRA_NAME;
import static ru.yaal.contexthabit.ui.context.ContextActivity.HABITS_EXTRA_NAME;

public class ContextAdapter extends RecyclerView.Adapter<ContextAdapter.ContextViewHolder> {
    private List<ContextEntity> dataSet;

    static class ContextViewHolder extends RecyclerView.ViewHolder {
        Button view;

        ContextViewHolder(Button view) {
            super(view);
            this.view = view;
        }
    }

    ContextAdapter(List<ContextEntity> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ContextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Button contextButton = new Button(parent.getContext());
        return new ContextViewHolder(contextButton);
    }

    @Override
    public void onBindViewHolder(@NonNull ContextViewHolder holder, int position) {
        ContextEntity context = dataSet.get(position);
        holder.view.setText(context.name);
        List<HabitEntity> habits = ContextActivity.repository.getHabitsForContext(context);
        holder.view.setOnClickListener(new ContextButtonOnClickListener(context, habits));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ContextButtonOnClickListener implements View.OnClickListener {
        private final ContextEntity context;
        private final HabitList habits;

        ContextButtonOnClickListener(ContextEntity context, List<HabitEntity> habits) {
            this.context = context;
            this.habits = new HabitList(habits);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), HabitActivity.class);
            intent.putExtra(CONTEXT_EXTRA_NAME, context);
            intent.putExtra(HABITS_EXTRA_NAME, habits);
            view.getContext().startActivity(intent);
        }
    }
}

