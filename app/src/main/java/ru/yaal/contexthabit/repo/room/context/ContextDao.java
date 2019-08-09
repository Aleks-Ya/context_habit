package ru.yaal.contexthabit.repo.room.context;

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
    ContextEntity getById(long contextId);

    @Query("SELECT * FROM ContextEntity WHERE parentContextId = 0")
    List<ContextEntity> getRootContexts();

    @Query("SELECT * FROM ContextEntity WHERE parentContextId = :contextId")
    List<ContextEntity> getNestedContexts(long contextId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ContextEntity context);

    @Delete
    void delete(ContextEntity context);
}
