package ru.yaal.contexthabit.repo.room.action;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;
import java.util.List;

import ru.yaal.contexthabit.repo.room.converter.LocalDateTimeConverters;

@Dao
@TypeConverters(LocalDateTimeConverters.class)
public interface ActionDao {
    @Query("SELECT * FROM ActionEntity")
    List<ActionEntity> getAll();

    @Query("SELECT * FROM ActionEntity WHERE id = :actionId")
    ActionEntity getById(long actionId);

    @Query("SELECT SUM(valueChange) FROM ActionEntity " +
            "WHERE habitId = :habitId AND date >= :lastRenewDate AND type = 'NEGATIVE'")
    int getNegativeValue(long habitId, LocalDateTime lastRenewDate);

    @Query("SELECT SUM(valueChange) FROM ActionEntity " +
            "WHERE habitId = :habitId AND date >= :lastRenewDate AND type = 'POSITIVE'")
    int getPositiveValue(long habitId, LocalDateTime lastRenewDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ActionEntity actions);

    @Delete
    void delete(ActionEntity action);
}
