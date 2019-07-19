package ru.yaal.contexthabit.repo.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContextDao {
    @Query("SELECT * FROM ContextEntity")
    List<ContextEntity> getAll();

    @Query("SELECT * FROM ContextEntity WHERE id = :contextId")
    ContextEntity getById(int contextId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContextEntity... contexts);

    @Delete
    void delete(ContextEntity context);
}
