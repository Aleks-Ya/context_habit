package ru.yaal.contexthabit.repo.room.context;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

@ToString
@EqualsAndHashCode
@Entity(primaryKeys = {"contextId", "habitId"},
        foreignKeys = {
                @ForeignKey(entity = ContextEntity.class,
                        parentColumns = "id",
                        childColumns = "contextId"),
                @ForeignKey(entity = HabitEntity.class,
                        parentColumns = "id",
                        childColumns = "habitId")
        })
public class ContextHabitJoin {
    public int contextId;
    public int habitId;
}

