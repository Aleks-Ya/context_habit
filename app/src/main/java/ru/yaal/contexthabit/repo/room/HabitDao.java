package ru.yaal.contexthabit.repo.room;

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
    HabitEntity getById(int habitId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HabitEntity... habits);

    @Delete
    void delete(HabitEntity habit);
}
