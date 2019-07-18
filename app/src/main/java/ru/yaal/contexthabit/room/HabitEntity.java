package ru.yaal.contexthabit.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
public class HabitEntity implements Serializable {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public String name;
}

