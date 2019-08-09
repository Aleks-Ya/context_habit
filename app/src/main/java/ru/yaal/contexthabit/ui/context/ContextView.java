package ru.yaal.contexthabit.ui.context;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import ru.yaal.contexthabit.android.R;

public class ContextView extends ConstraintLayout {
    public ContextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button getContextButton() {
        return findViewById(R.id.context_button);
    }

}
