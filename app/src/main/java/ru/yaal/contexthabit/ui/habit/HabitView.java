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
        return findViewById(R.id.habitNameTextView);
    }

    public Button getNegativeMinusButton() {
        return findViewById(R.id.negativeMinusButton);
    }

    public Button getNegativePlusButton() {
        return findViewById(R.id.negativePlusButton);
    }

    public TextView getNegativeValueTextView() {
        return findViewById(R.id.negativeValueTextView);
    }

    public Button getPositiveMinusButton() {
        return findViewById(R.id.positiveMinusButton);
    }

    public Button getPositivePlusButton() {
        return findViewById(R.id.positivePlusButton);
    }

    public TextView getPositiveValueTextView() {
        return findViewById(R.id.positiveValueTextView);
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
