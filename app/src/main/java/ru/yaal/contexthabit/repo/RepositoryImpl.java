package ru.yaal.contexthabit.repo;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.List;

import ru.yaal.contexthabit.repo.room.action.ActionDao;
import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextDao;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoinDao;
import ru.yaal.contexthabit.repo.room.habit.HabitDao;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewDao;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleDao;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

import static ru.yaal.contexthabit.repo.room.EntityBuilder.createContextHabitJoin;

public class RepositoryImpl implements Repository {
    private final ContextDao contextDao;
    private final HabitDao habitDao;
    private final ContextHabitJoinDao contextHabitJoinDao;
    private final ActionDao actionDao;
    private final ScheduleDao scheduleDao;
    private final HabitRenewDao habitRenewDao;

    public RepositoryImpl(ContextDao contextDao, HabitDao habitDao,
                          ContextHabitJoinDao contextHabitJoinDao, ActionDao actionDao,
                          ScheduleDao scheduleDao, HabitRenewDao habitRenewDao) {
        this.contextDao = contextDao;
        this.habitDao = habitDao;
        this.contextHabitJoinDao = contextHabitJoinDao;
        this.actionDao = actionDao;
        this.scheduleDao = scheduleDao;
        this.habitRenewDao = habitRenewDao;
    }

    @Override
    public ContextEntity saveContext(ContextEntity contextEntity) {
        contextEntity.id = contextDao.insert(contextEntity);
        return contextEntity;
    }

    @Override
    public HabitEntity saveHabit(HabitEntity habitEntity) {
        habitEntity.id = habitDao.insert(habitEntity);
        return habitEntity;
    }

    @Override
    public List<HabitEntity> getAllHabits() {
        return habitDao.getAll();
    }

    @Override
    public List<HabitRenewEntity> getAllHabitRenews() {
        return habitRenewDao.getAll();
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
    public List<ContextEntity> getRootContexts() {
        return contextDao.getRootContexts();
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
    public int getNegativeValue(long habitId, LocalDateTime lastRenewDate) {
        return actionDao.getNegativeValue(habitId, lastRenewDate);
    }

    @Override
    public int getPositiveValue(long habitId, LocalDateTime lastRenewDate) {
        return actionDao.getPositiveValue(habitId, lastRenewDate);
    }

    @Override
    public ScheduleEntity saveSchedule(ScheduleEntity scheduleEntity) {
        scheduleEntity.id = scheduleDao.insert(scheduleEntity);
        return scheduleEntity;
    }

    @Override
    public ScheduleEntity getSchedule(HabitEntity habitEntity) {
        if (habitEntity.scheduleId == null) {
            return null;
        }
        return scheduleDao.getById(habitEntity.scheduleId);
    }

    @Override
    public HabitRenewEntity saveHabitRenew(HabitRenewEntity habitRenewEntity) {
        habitRenewEntity.id = habitRenewDao.insert(habitRenewEntity);
        Log.i(getClass().getSimpleName(), "HabitRenewEntity is saved: " + habitRenewEntity);
        return habitRenewEntity;
    }

    @Override
    public HabitRenewEntity getLastHabitRenew(HabitEntity habitEntity) {
        return habitRenewDao.getLastRenewForHabit(habitEntity.id);
    }

}
