package ru.yaal.contexthabit.service;

import com.cronutils.model.time.ExecutionTime;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import lombok.SneakyThrows;
import ru.yaal.contexthabit.repo.Repository;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;

public class RenewServiceImpl implements RenewService {
    private final Repository repository;

    public RenewServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    @SneakyThrows
    public LocalDateTime getNextHabitRenew(HabitEntity habit) {
        ScheduleEntity schedule = repository.getSchedule(habit);
        ZonedDateTime now = ZonedDateTime.now();
        ExecutionTime executionTime = ExecutionTime.forCron(schedule.cron);
        Optional<ZonedDateTime> nextExecutionOpt = executionTime.nextExecution(now);
        if (!nextExecutionOpt.isPresent()) {
            throw new IllegalStateException("No next execution for schedule: " + schedule);
        }
        ZonedDateTime nextExecution = nextExecutionOpt.get();
        return nextExecution.toLocalDateTime();
    }
}
