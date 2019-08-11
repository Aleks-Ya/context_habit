package ru.yaal.contexthabit.repo;

import java.time.LocalDateTime;
import java.util.List;

import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

public interface Repository {
    ContextEntity saveContext(ContextEntity contextEntity);

    List<ContextEntity> getAllContexts();

    List<ContextEntity> getRootContexts();

    List<ContextEntity> getNestedContexts(ContextEntity parentContext);

    HabitEntity saveHabit(HabitEntity habitEntity);

    List<HabitEntity> getAllHabits();

    List<HabitRenewEntity> getAllHabitRenews();

    void link(ContextEntity contextEntity, HabitEntity habitEntity, Long order);

    List<HabitEntity> getHabitsForContext(ContextEntity contextEntity);

    ActionEntity saveAction(ActionEntity actionEntity);

    int getNegativeValue(long habitId, LocalDateTime lastRenewDate);

    int getPositiveValue(long habitId, LocalDateTime lastRenewDate);

    ScheduleEntity saveSchedule(ScheduleEntity scheduleEntity);

    ScheduleEntity getSchedule(HabitEntity habitEntity);

    HabitRenewEntity saveHabitRenew(HabitRenewEntity habitRenewEntity);

    HabitRenewEntity getLastHabitRenew(HabitEntity habitEntity);
}
