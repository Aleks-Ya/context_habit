package ru.yaal.contexthabit.ui.habit;

import android.widget.TextView;

import androidx.lifecycle.Observer;

import java.util.Locale;

public class IntegerObserver implements Observer<Integer> {
    private final TextView textView;

    IntegerObserver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onChanged(Integer value) {
        String str = String.format(Locale.getDefault(), "%d", value);
        textView.setText(str);
    }
}
