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
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public Long parentContextId;

    @ColumnInfo
    public String name;

    public static final ContextEntity emptyContext = createEmptyContext();

    private static ContextEntity createEmptyContext() {
        ContextEntity context = new ContextEntity();
        context.id = 0L;
        return context;
    }
}

