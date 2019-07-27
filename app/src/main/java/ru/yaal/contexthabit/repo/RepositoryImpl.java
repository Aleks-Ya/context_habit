package ru.yaal.contexthabit.repo;

import android.util.Log;

import java.util.List;

import ru.yaal.contexthabit.repo.room.action.ActionDao;
import ru.yaal.contexthabit.repo.room.action.ActionEntity;
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
    private final ActionDao actionDao;

    public RepositoryImpl(ContextDao contextDao, HabitDao habitDao,
                          ContextHabitJoinDao contextHabitJoinDao, ActionDao actionDao) {
        this.contextDao = contextDao;
        this.habitDao = habitDao;
        this.contextHabitJoinDao = contextHabitJoinDao;
        this.actionDao = actionDao;
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

    @Override
    public ActionEntity saveAction(ActionEntity actionEntity) {
        long id = actionDao.insert(actionEntity);
        ActionEntity saved = actionDao.getById(id);
        Log.i("Repository", "ActionEntity is saved: " + saved);
        return saved;
    }

    @Override
    public int getNegativeValue(long contextId, long habitId) {
        return actionDao.getNegativeValue(contextId, habitId);
    }

}
