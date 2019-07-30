package ru.yaal.contexthabit.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

import static ru.yaal.contexthabit.service.Singleton.repository;

public class HabitRenewWorker extends Worker {

    public HabitRenewWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        Log.i(getClass().getSimpleName(), "Updating HabitRenews....");
        List<HabitEntity> allHabits = repository.getAllHabits();
        for (HabitEntity habit : allHabits) {
            LocalDateTime nextHabitRenew = getNextHabitRenew(habit);
            LocalDateTime now = LocalDateTime.now();
            while (nextHabitRenew.isBefore(now) || nextHabitRenew.isEqual(now)) {
                HabitRenewEntity habitRenew = new HabitRenewEntity();
                habitRenew.date = nextHabitRenew;
                habitRenew.habitId = habit.id;
                ScheduleEntity schedule = repository.getSchedule(habit);
                habitRenew.scheduleId = schedule.id;
                repository.saveHabitRenew(habitRenew);
                nextHabitRenew = getNextHabitRenew(habit);
            }
        }
        return Result.success();
    }

    private LocalDateTime getNextHabitRenew(HabitEntity habit) {
        HabitRenewEntity lastHabitRenew = repository.getLastHabitRenew(habit);
        if (lastHabitRenew == null) {
            return LocalDateTime.now();
        }
        ZonedDateTime lastHabitRenewZonedDateTime = lastHabitRenew.date.atZone(ZoneId.systemDefault());
        return Singleton.renewService.getNextHabitRenew(habit, lastHabitRenewZonedDateTime);
    }
}
