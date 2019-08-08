package ru.yaal.contexthabit.ui.habit;

import android.widget.ProgressBar;

import androidx.lifecycle.Observer;

class ProgressBarValueObserver implements Observer<Integer> {

    private final ProgressBar progressBar;

    ProgressBarValueObserver(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onChanged(Integer progress) {
        progressBar.setProgress(progress);
    }
}
