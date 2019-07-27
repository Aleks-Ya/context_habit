package ru.yaal.contexthabit.repo.room.context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
public class ContextEntity implements Serializable {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public Integer parentContextId;

    @ColumnInfo
    public String name;

    public static final ContextEntity emptyContext = new ContextEntity();
}

