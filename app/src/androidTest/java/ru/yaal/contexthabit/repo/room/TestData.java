package ru.yaal.contexthabit.repo.room;

import static ru.yaal.contexthabit.repo.room.EntityBuilder.createHabit;

public class TestData {
    public static final ContextEntity context1 = EntityBuilder.createContext(1,
            "Eat", ContextEntity.emptyContext.id);

    public static final ContextEntity context2 = EntityBuilder.createContext(2,
            "Eat Finished", context1.id);

    public static final ContextEntity context3 = EntityBuilder.createContext(3,
            "Eat Want", ContextEntity.emptyContext.id);

    public static final HabitEntity habit1 = createHabit(1, "Eat hungry");
    public static final HabitEntity habit2 = createHabit(2, "Not eat sugar");
}
