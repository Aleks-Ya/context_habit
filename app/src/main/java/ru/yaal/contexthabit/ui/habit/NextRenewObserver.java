package ru.yaal.contexthabit.ui.habit;

import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NextRenewObserver implements Observer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy E HH:mm");
    private final TextView textView;

    NextRenewObserver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onChanged(LocalDateTime nextRenew) {
        if (nextRenew != null) {
            String date = nextRenew.format(formatter);
            String nextRenewStr = String.format("Next renew: %s", date);
            textView.setText(nextRenewStr);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
