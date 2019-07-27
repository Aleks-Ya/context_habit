package ru.yaal.contexthabit.ui.habit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

class HabitViewModel extends ViewModel {
    final MutableLiveData<ContextEntity> contextEntity = new MutableLiveData<>();
    final MutableLiveData<HabitEntity> habitEntity = new MutableLiveData<>();
    final MutableLiveData<Integer> negativeCount = new MutableLiveData<>();
    final MutableLiveData<Integer> positiveCount = new MutableLiveData<>();
}
