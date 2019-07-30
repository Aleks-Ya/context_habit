package ru.yaal.contexthabit.service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

public interface RenewService {
    LocalDateTime getNextHabitRenew(HabitEntity habit, ZonedDateTime now);
}
