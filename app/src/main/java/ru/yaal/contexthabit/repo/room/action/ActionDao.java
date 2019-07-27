package ru.yaal.contexthabit.repo.room.action;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActionDao {
    @Query("SELECT * FROM ActionEntity")
    List<ActionEntity> getAll();

    @Query("SELECT * FROM ActionEntity WHERE id = :actionId")
    ActionEntity getById(long actionId);

    @Query("SELECT SUM(valueChange) FROM ActionEntity " +
            "WHERE contextId = :contextId AND habitId = :habitId AND type = 'NEGATIVE'")
    int getNegativeValue(long contextId, long habitId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ActionEntity actions);

    @Delete
    void delete(ActionEntity action);
}
