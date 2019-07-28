package ru.yaal.contexthabit.repo.room.habit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HabitDao {
    @Query("SELECT * FROM HabitEntity")
    List<HabitEntity> getAll();

    @Query("SELECT * FROM HabitEntity WHERE id = :habitId")
    HabitEntity getById(long habitId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(HabitEntity habit);

    @Delete
    void delete(HabitEntity habit);
}
