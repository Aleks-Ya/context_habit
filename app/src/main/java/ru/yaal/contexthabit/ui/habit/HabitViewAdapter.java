package ru.yaal.contexthabit.ui.habit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.HabitEntity;

public class HabitViewAdapter extends RecyclerView.Adapter<HabitViewAdapter.HabitViewHolder> {
    private List<HabitEntity> habits;

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        HabitViewHolder(View view) {
            super(view);
        }
    }

    HabitViewAdapter(List<HabitEntity> habits) {
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
        ViewGroup viewGroup = (ViewGroup) holder.itemView;

        HabitEntity habit = habits.get(position);

        TextView habitNameTextView = viewGroup.findViewById(R.id.habitNameTextView);
        habitNameTextView.setText(habit.name);

        TextView negativeValueTextView = viewGroup.findViewById(R.id.negativeValueTextView);
        negativeValueTextView.setText("val " + new Date());

    }

    @Override
    public int getItemCount() {
        return habits.size();
    }
}
