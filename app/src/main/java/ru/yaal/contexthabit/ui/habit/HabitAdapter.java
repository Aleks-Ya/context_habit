package ru.yaal.contexthabit.ui.habit;

import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.repo.room.HabitEntity;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private List<HabitEntity> habits;

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        Button view;

        HabitViewHolder(Button view) {
            super(view);
            this.view = view;
        }
    }

    HabitAdapter(List<HabitEntity> habits) {
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Button habitButton = new Button(parent.getContext());
        return new HabitViewHolder(habitButton);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        holder.view.setText(habits.get(position).name);

    }

    @Override
    public int getItemCount() {
        return habits.size();
    }
}

