package ru.yaal.contexthabit;

import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.room.HabitEntity;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ContextViewHolder> {
    private List<HabitEntity> habits;

    public static class ContextViewHolder extends RecyclerView.ViewHolder {
        public Button view;

        public ContextViewHolder(Button view) {
            super(view);
            this.view = view;
        }
    }

    public HabitAdapter(List<HabitEntity> habits) {
        this.habits = habits;
    }

    @Override
    public ContextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button button = new Button(parent.getContext());
        return new ContextViewHolder(button);
    }

    @Override
    public void onBindViewHolder(ContextViewHolder holder, int position) {
        holder.view.setText(habits.get(position).name);

    }

    @Override
    public int getItemCount() {
        return habits.size();
    }
}

