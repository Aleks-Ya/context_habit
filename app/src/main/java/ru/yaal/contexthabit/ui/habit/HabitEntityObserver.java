package ru.yaal.contexthabit.ui.habit;

import android.widget.TextView;

import androidx.lifecycle.Observer;

import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

public class HabitEntityObserver implements Observer<HabitEntity> {
    private final TextView textView;

    HabitEntityObserver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onChanged(HabitEntity habitEntity) {
        textView.setText(habitEntity.name);
    }
}
