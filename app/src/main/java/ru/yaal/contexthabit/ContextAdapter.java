package ru.yaal.contexthabit;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.room.ContextEntity;
import ru.yaal.contexthabit.room.HabitEntity;

import static ru.yaal.contexthabit.MainActivity.HABITS_EXTRA_NAME;

public class ContextAdapter extends RecyclerView.Adapter<ContextAdapter.ContextViewHolder> {
    private List<ContextEntity> dataset;

    public static class ContextViewHolder extends RecyclerView.ViewHolder {
        public Button view;

        public ContextViewHolder(Button view) {
            super(view);
            this.view = view;
        }
    }

    public ContextAdapter(List<ContextEntity> dataset) {
        this.dataset = dataset;
    }

    @Override
    public ContextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button button = new Button(parent.getContext());
        return new ContextViewHolder(button);
    }

    @Override
    public void onBindViewHolder(ContextViewHolder holder, int position) {
        ContextEntity context = dataset.get(position);
        holder.view.setText(context.name);
        List<HabitEntity> habits = MainActivity.database.contextHabitJoinDao().getHabitsForContext(context.id);
        holder.view.setOnClickListener(new ContextButtonOnClickListener(habits));

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ContextButtonOnClickListener implements View.OnClickListener {
        private final HabitList habits;

        public ContextButtonOnClickListener(List<HabitEntity> habits) {
            this.habits = new HabitList(habits);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), HabitsActivity.class);
            intent.putExtra(HABITS_EXTRA_NAME, habits);
            view.getContext().startActivity(intent);
        }
    }
}

