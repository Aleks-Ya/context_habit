package ru.yaal.contexthabit.ui.habit;

import android.widget.ProgressBar;

import androidx.lifecycle.Observer;

import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

class ProgressBarHabitObserver implements Observer<HabitEntity> {

    private final ProgressBar progressBar;

    ProgressBarHabitObserver(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onChanged(HabitEntity habitEntity) {
        progressBar.setMax(habitEntity.targetValue);
    }
}
