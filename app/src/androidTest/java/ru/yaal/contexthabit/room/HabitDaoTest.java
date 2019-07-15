package ru.yaal.contexthabit.room;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HabitDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        HabitEntity expHabit = new HabitEntity();
        expHabit.id = 1;
        expHabit.name = "Eat";

        habitDao.insert(expHabit);

        HabitEntity actHabit = habitDao.getById(expHabit.id);
        assertThat(actHabit, equalTo(expHabit));
    }

    @Test
    public void delete() {
        HabitEntity habit = new HabitEntity();
        habit.id = 2;

        habitDao.insert(habit);
        assertThat(habitDao.getById(habit.id), equalTo(habit));

        habitDao.delete(habit);
        assertNull(habitDao.getById(habit.id));
    }

    @Test
    public void getAll() {
        HabitEntity habit1 = new HabitEntity();
        habit1.id = 3;
        habit1.name = "Eat hungry";

        HabitEntity habit2 = new HabitEntity();
        habit2.id = 4;
        habit2.name = "Not eat sugar";

        habitDao.insert(habit1);
        habitDao.insert(habit2);
        List<HabitEntity> allContexts = habitDao.getAll();
        assertThat(allContexts, Matchers.containsInAnyOrder(habit1, habit2));
    }


}