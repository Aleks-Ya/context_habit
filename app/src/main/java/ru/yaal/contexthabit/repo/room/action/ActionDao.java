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
    ActionEntity getById(int actionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ActionEntity... actions);

    @Delete
    void delete(ActionEntity action);
}
