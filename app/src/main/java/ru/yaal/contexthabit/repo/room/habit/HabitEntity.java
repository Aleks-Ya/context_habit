package ru.yaal.contexthabit.repo.room.habit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity(foreignKeys = @ForeignKey(entity = ScheduleEntity.class,
        parentColumns = "id", childColumns = "scheduleId"))
public class HabitEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public Long scheduleId;

    @ColumnInfo
    public Integer targetValue;
}

