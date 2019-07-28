package ru.yaal.contexthabit.repo.room;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static ru.yaal.contexthabit.repo.room.EntityBuilder.createHabit;
import static ru.yaal.contexthabit.repo.room.context.ContextEntity.emptyContext;

public class TestData {
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
        return createHabit(null, "Eat hungry");
    }

    public static HabitEntity createHabitNoId2() {
        return createHabit(null, "Not eat sugar");
    }
}
