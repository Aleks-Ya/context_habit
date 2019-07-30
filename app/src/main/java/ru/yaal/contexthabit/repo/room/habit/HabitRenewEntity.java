package ru.yaal.contexthabit.repo.room.habit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.yaal.contexthabit.repo.room.converter.LocalDateTimeConverters;

@ToString
@EqualsAndHashCode
@Entity(foreignKeys = {
        @ForeignKey(entity = ScheduleEntity.class,
                parentColumns = "id", childColumns = "scheduleId"),
        @ForeignKey(entity = HabitEntity.class,
                parentColumns = "id", childColumns = "habitId"
        )
})
public class HabitRenewEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public Long habitId;

    @ColumnInfo
    public Long scheduleId;

    @ColumnInfo
    @TypeConverters(LocalDateTimeConverters.class)
    public LocalDateTime date;

}

