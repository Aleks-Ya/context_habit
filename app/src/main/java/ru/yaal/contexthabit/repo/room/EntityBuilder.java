package ru.yaal.contexthabit.repo.room;

import java.time.LocalDateTime;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoin;
import ru.yaal.contexthabit.repo.room.converter.CronConverters;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

public class EntityBuilder {
    private static final CronConverters cronConverter = new CronConverters();

    public static ContextEntity createContext(Long contextId, String contextName,
                                              long parentContextId) {
        ContextEntity context = new ContextEntity();
        context.id = contextId;
        context.name = contextName;
        context.parentContextId = parentContextId;
        return context;
    }

    public static ContextEntity createContext(String contextName, long parentContextId) {
        return createContext(null, contextName, parentContextId);
    }

    public static HabitEntity createHabit(Long habitId, String habitName, Long scheduleId) {
        HabitEntity habit = new HabitEntity();
        habit.id = habitId;
        habit.name = habitName;
        habit.scheduleId = scheduleId;
        return habit;
    }

    public static ContextHabitJoin createContextHabitJoin(Long contextId, Long habitId) {
        ContextHabitJoin join = new ContextHabitJoin();
        join.contextId = contextId;
        join.habitId = habitId;
        return join;
    }

    public static ScheduleEntity createScheduleEntity(Long scheduleId, String name, String cron) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.id = scheduleId;
        schedule.name = name;
        schedule.cron = cronConverter.fromString(cron);
        return schedule;
    }

    public static HabitRenewEntity createHabitRenewEntity(Long habitId, Long scheduleId, LocalDateTime date) {
        HabitRenewEntity habitRenew = new HabitRenewEntity();
        habitRenew.habitId = habitId;
        habitRenew.scheduleId = scheduleId;
        habitRenew.date = date;
        return habitRenew;
    }

}
