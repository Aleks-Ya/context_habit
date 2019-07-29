package ru.yaal.contexthabit.repo.room.habit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.cronutils.model.Cron;

import java.io.Serializable;
import java.util.Objects;

import ru.yaal.contexthabit.repo.room.converter.CronConverters;

@Entity
public class ScheduleEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    @TypeConverters(CronConverters.class)
    public Cron cron;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                cron.equivalent(that.cron);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cron.asString());
    }
}

