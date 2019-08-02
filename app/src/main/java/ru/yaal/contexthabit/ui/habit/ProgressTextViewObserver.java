package ru.yaal.contexthabit.ui.habit;

import android.widget.TextView;

import androidx.lifecycle.Observer;

public class ProgressTextViewObserver implements Observer<String> {

    private final TextView progressTextView;

    ProgressTextViewObserver(TextView progressTextView) {
        this.progressTextView = progressTextView;
    }

    @Override
    public void onChanged(String progress) {
        progressTextView.setText(progress);
    }
}
