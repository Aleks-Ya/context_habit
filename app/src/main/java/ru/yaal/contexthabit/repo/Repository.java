package ru.yaal.contexthabit.repo;

import java.util.List;

import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

public interface Repository {
    ContextEntity saveContext(ContextEntity contextEntity);

    HabitEntity saveHabit(HabitEntity habitEntity);

    void link(ContextEntity contextEntity, HabitEntity habitEntity);

    List<ContextEntity> getAllContexts();

    List<HabitEntity> getHabitsForContext(ContextEntity contextEntity);

    ActionEntity saveAction(ActionEntity actionEntity);

    int getNegativeValue(long habitId);

    int getPositiveValue(long habitId);

    ScheduleEntity saveSchedule(ScheduleEntity scheduleEntity);
}
