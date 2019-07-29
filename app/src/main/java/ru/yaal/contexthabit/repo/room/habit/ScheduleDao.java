package ru.yaal.contexthabit.repo.room.habit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScheduleDao {
    @Query("SELECT * FROM ScheduleEntity")
    List<ScheduleEntity> getAll();

    @Query("SELECT * FROM ScheduleEntity WHERE id = :scheduleId")
    ScheduleEntity getById(long scheduleId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ScheduleEntity schedule);

    @Delete
    void delete(ScheduleEntity schedule);
}
