package ru.yaal.contexthabit.repo.room.context;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

@Dao
public interface ContextHabitJoinDao {
    @Query("SELECT * FROM ContextHabitJoin")
    List<ContextHabitJoin> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContextHabitJoin... contextHabitJoins);

    @Query("SELECT * FROM ContextEntity " +
            "INNER JOIN ContextHabitJoin " +
            "ON ContextEntity.id=ContextHabitJoin.contextId " +
            "WHERE ContextHabitJoin.habitId=:habitId")
    List<ContextEntity> getContextsForHabit(final long habitId);

    @Query("SELECT * FROM HabitEntity " +
            "INNER JOIN ContextHabitJoin " +
            "ON HabitEntity.id=ContextHabitJoin.habitId " +
            "WHERE ContextHabitJoin.contextId=:contextId")
    List<HabitEntity> getHabitsForContext(final long contextId);

    @Delete
    void delete(ContextHabitJoin contextHabitJoin);
}
