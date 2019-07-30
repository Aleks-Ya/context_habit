package ru.yaal.contexthabit.service;

import java.time.LocalDateTime;

import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

public interface RenewService {
    LocalDateTime getNextHabitRenew(HabitEntity habit);
}
