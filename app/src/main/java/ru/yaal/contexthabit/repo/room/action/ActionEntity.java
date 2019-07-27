package ru.yaal.contexthabit.repo.room.action;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.yaal.contexthabit.repo.room.converter.ActionTypeConverters;
import ru.yaal.contexthabit.repo.room.converter.LocalDateTimeConverters;

@Entity
@ToString
@EqualsAndHashCode
public class ActionEntity {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public Integer contextId;

    @ColumnInfo
    public Integer habitId;

    @ColumnInfo
    @TypeConverters(LocalDateTimeConverters.class)
    public LocalDateTime date;

    @ColumnInfo
    @TypeConverters(ActionTypeConverters.class)
    public ActionType type;

    @ColumnInfo
    public Integer valueChange;

    public enum ActionType {
        POSITIVE, NEGATIVE
    }
}

