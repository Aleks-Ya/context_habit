package ru.yaal.contexthabit.repo;

import java.util.List;

import ru.yaal.contexthabit.repo.room.context.ContextDao;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoinDao;
import ru.yaal.contexthabit.repo.room.habit.HabitDao;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static ru.yaal.contexthabit.repo.room.EntityBuilder.createContextHabitJoin;

public class RepositoryImpl implements Repository {
    private final ContextDao contextDao;
    private final HabitDao habitDao;
    private final ContextHabitJoinDao contextHabitJoinDao;

    public RepositoryImpl(ContextDao contextDao, HabitDao habitDao,
                          ContextHabitJoinDao contextHabitJoinDao) {
        this.contextDao = contextDao;
        this.habitDao = habitDao;
        this.contextHabitJoinDao = contextHabitJoinDao;
    }

    @Override
    public void saveContext(ContextEntity... contextEntities) {
        contextDao.insert(contextEntities);
    }

    @Override
    public void saveHabit(HabitEntity... habitEntity) {
        habitDao.insert(habitEntity);
    }

    @Override
    public void link(ContextEntity contextEntity, HabitEntity habitEntity) {
        contextHabitJoinDao.insert(createContextHabitJoin(contextEntity.id, habitEntity.id));
    }

    @Override
    public List<ContextEntity> getAllContexts() {
        return contextDao.getAll();
    }

    @Override
    public List<HabitEntity> getHabitsForContext(ContextEntity contextEntity) {
        return contextHabitJoinDao.getHabitsForContext(contextEntity.id);
    }

}
