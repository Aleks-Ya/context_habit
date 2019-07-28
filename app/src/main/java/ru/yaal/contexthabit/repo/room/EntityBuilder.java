package ru.yaal.contexthabit.repo.room;

import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoin;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

public class EntityBuilder {

    public static ContextEntity createContext(Long contextId, String contextName,
                                              long parentContextId) {
        ContextEntity context = new ContextEntity();
        context.id = contextId;
        context.name = contextName;
        context.parentContextId = parentContextId;
        return context;
    }

    public static HabitEntity createHabit(Long habitId, String habitName) {
        HabitEntity habit = new HabitEntity();
        habit.id = habitId;
        habit.name = habitName;
        return habit;
    }

    public static ContextHabitJoin createContextHabitJoin(Long contextId, Long habitId) {
        ContextHabitJoin join = new ContextHabitJoin();
        join.contextId = contextId;
        join.habitId = habitId;
        return join;
    }

}
