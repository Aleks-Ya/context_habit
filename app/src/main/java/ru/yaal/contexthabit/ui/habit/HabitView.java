package ru.yaal.contexthabit.ui.habit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import ru.yaal.contexthabit.android.R;

public class HabitView extends ConstraintLayout {
    public HabitView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView getHabitNameTextView() {
        return findViewById(R.id.habit_name_text_view);
    }

    public Button getNegativeMinusButton() {
        return findViewById(R.id.negative_minus_button);
    }

    public Button getNegativePlusButton() {
        return findViewById(R.id.negative_plus_button);
    }

    public TextView getNegativeValueTextView() {
        return findViewById(R.id.negative_value_text_view);
    }

    public Button getPositiveMinusButton() {
        return findViewById(R.id.positive_minus_button);
    }

    public Button getPositivePlusButton() {
        return findViewById(R.id.positive_plus_button);
    }

    public TextView getPositiveValueTextView() {
        return findViewById(R.id.positive_value_text_view);
    }

    public TextView getNextRenewTextView() {
        return findViewById(R.id.next_renew_text_view);
    }

    public ProgressBar getProgressBar() {
        return findViewById(R.id.habit_progress_bar);
    }

    public TextView getProgressTextView() {
        return findViewById(R.id.progress_text_view);
    }

}
