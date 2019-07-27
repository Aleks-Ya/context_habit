package ru.yaal.contexthabit.ui.habit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
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

}
