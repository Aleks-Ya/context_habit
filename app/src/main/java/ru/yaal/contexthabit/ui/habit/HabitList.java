package ru.yaal.contexthabit.ui.habit;

import java.io.Serializable;
import java.util.List;

import ru.yaal.contexthabit.repo.room.HabitEntity;

public class HabitList implements Serializable {
    private final List<HabitEntity> habits;

    public HabitList(List<HabitEntity> habits) {
        this.habits = habits;
    }

    public List<HabitEntity> getHabits() {
        return habits;
    }
}
