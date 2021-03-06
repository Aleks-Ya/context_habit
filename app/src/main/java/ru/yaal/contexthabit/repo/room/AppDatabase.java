package ru.yaal.contexthabit.repo.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.yaal.contexthabit.repo.room.action.ActionDao;
import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextDao;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoin;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoinDao;
import ru.yaal.contexthabit.repo.room.habit.HabitDao;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewDao;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleDao;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

@Database(entities = {ActionEntity.class, ContextEntity.class,
        HabitEntity.class, ContextHabitJoin.class, ScheduleEntity.class,
        HabitRenewEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContextDao contextDao();

    public abstract HabitDao habitDao();

    public abstract ActionDao actionDao();

    public abstract ContextHabitJoinDao contextHabitJoinDao();

    public abstract ScheduleDao scheduleDao();

    public abstract HabitRenewDao habitRenewDao();
}

