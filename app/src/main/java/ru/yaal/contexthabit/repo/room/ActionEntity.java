package ru.yaal.contexthabit.repo.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.yaal.contexthabit.repo.room.converter.LocalDateTimeConverters;

@Entity
@ToString
@EqualsAndHashCode
public class ActionEntity {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public int contextId;

    @ColumnInfo
    public int habitId;

    @ColumnInfo
    @TypeConverters(LocalDateTimeConverters.class)
    public LocalDateTime date;

    @ColumnInfo
    public int valueChange;
}

