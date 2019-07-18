package ru.yaal.contexthabit.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ActionEntity.class, ContextEntity.class,
        HabitEntity.class, ContextHabitJoin.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContextDao contextDao();

    public abstract HabitDao habitDao();

    public abstract ActionDao actionDao();

    public abstract ContextHabitJoinDao contextHabitJoinDao();
}

