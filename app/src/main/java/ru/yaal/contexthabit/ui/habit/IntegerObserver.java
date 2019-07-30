package ru.yaal.contexthabit.ui.habit;

import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import java.util.Locale;

public class IntegerObserver implements Observer<Integer> {
    private static final int MINIMAL_VALUE = 0;
    private final TextView textView;
    private final Button minusButton;

    IntegerObserver(TextView textView, Button minusButton) {
        this.textView = textView;
        this.minusButton = minusButton;
    }

    @Override
    public void onChanged(Integer value) {
        String text = String.format(Locale.getDefault(), "%d", value);
        textView.setText(text);
        boolean enabled = value > MINIMAL_VALUE;
        minusButton.setEnabled(enabled);
    }
}
