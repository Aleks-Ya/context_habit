package ru.yaal.contexthabit.repo;

import java.util.List;

import ru.yaal.contexthabit.repo.room.ContextEntity;
import ru.yaal.contexthabit.repo.room.HabitEntity;

public interface Repository {
    void saveContext(ContextEntity... contextEntities);

    void saveHabit(HabitEntity... habitEntity);

    void link(ContextEntity contextEntity, HabitEntity habitEntity);

    List<ContextEntity> getAllContexts();

    List<HabitEntity> getHabitsForContext(ContextEntity contextEntity);
}
