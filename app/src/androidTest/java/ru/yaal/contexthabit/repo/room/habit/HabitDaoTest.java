package ru.yaal.contexthabit.repo.room.habit;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createHabit;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;

public class HabitDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        HabitEntity habit = createHabit(null, "Eat hungry");
        long habitId = habitDao.insert(habit);
        habit.id = habitId;
        assertThat(habitDao.getById(habitId), equalTo(habit));
    }

    @Test
    public void delete() {
        HabitEntity habit1 = createHabitNoId1();
        long habitId = habitDao.insert(habit1);
        habit1.id = habitId;
        assertThat(habitDao.getById(habitId), equalTo(habit1));

        habitDao.delete(habit1);
        assertNull(habitDao.getById(habit1.id));
    }

    @Test
    public void getAll() {
        HabitEntity habit1 = createHabitNoId1();
        habit1.id = habitDao.insert(habit1);

        HabitEntity habit2 = createHabitNoId1();
        habit2.id = habitDao.insert(habit2);

        assertThat(habitDao.getAll(), containsInAnyOrder(habit1, habit2));
    }


}