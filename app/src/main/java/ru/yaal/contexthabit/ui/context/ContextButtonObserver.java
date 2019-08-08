package ru.yaal.contexthabit.ui.context;

import android.widget.Button;

import androidx.lifecycle.Observer;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;

class ContextButtonObserver implements Observer<ContextEntity> {
    private final Button contextButton;

    ContextButtonObserver(Button contextButton) {
        this.contextButton = contextButton;
    }

    @Override
    public void onChanged(ContextEntity context) {
        contextButton.setText(context.name);
    }
}
