package ru.yaal.contexthabit.repo.room;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

import static ru.yaal.contexthabit.repo.room.EntityBuilder.createHabit;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createScheduleEntity;
import static ru.yaal.contexthabit.repo.room.context.ContextEntity.emptyContext;

public class TestData {
    public static Long dailyScheduleId = 1L;
    public static Long weeklyScheduleId = 2L;

    public static ContextEntity createContextNoId1() {
        return EntityBuilder.createContext(null, "Eat", emptyContext.id);
    }

    public static ContextEntity createContextNoId2() {
        return EntityBuilder.createContext(null, "Eat Finished", emptyContext.id);
    }

    public static ContextEntity createContextNoId3() {
        return EntityBuilder.createContext(null, "Eat Want", emptyContext.id);
    }

    public static HabitEntity createHabitNoId1() {
        return createHabit(null, "Eat hungry", dailyScheduleId);
    }

    public static HabitEntity createHabitNoId2() {
        return createHabit(null, "Not eat sugar", weeklyScheduleId);
    }

    public static ScheduleEntity createScheduleNoIdDaily() {
        return createScheduleEntity(dailyScheduleId, "Daily", "0 4 * * *");
    }

    public static ScheduleEntity createScheduleNoIdWeekly() {
        return createScheduleEntity(weeklyScheduleId, "Weekly", "0 4 * * 1");
    }
}
