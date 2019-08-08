package ru.yaal.contexthabit.ui.context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;

class ContextViewModel extends ViewModel {
    final MutableLiveData<ContextEntity> contextEntity = new MutableLiveData<>();
}
