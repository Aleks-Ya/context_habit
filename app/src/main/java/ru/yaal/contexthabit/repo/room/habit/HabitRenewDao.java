package ru.yaal.contexthabit.repo.room.habit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HabitRenewDao {
    @Query("SELECT * FROM HabitRenewEntity")
    List<HabitRenewEntity> getAll();

    @Query("SELECT * FROM HabitRenewEntity WHERE id = :habitRenewId")
    HabitRenewEntity getById(long habitRenewId);

    @Query("SELECT * FROM HabitRenewEntity WHERE habitId = :habitId ORDER BY date DESC LIMIT 1")
    HabitRenewEntity getLastRenewForHabit(Long habitId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(HabitRenewEntity habitRenewEntity);


    @Delete
    void delete(HabitRenewEntity habitRenewEntity);
}
