package ru.yaal.contexthabit.repo.room.habit;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.habit1;
import static ru.yaal.contexthabit.repo.room.TestData.habit2;

public class HabitDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        habitDao.insert(habit1);
        assertThat(habitDao.getById(habit1.id), equalTo(habit1));
    }

    @Test
    public void delete() {
        habitDao.insert(habit1);
        assertThat(habitDao.getById(habit1.id), equalTo(habit1));

        habitDao.delete(habit1);
        assertNull(habitDao.getById(habit1.id));
    }

    @Test
    public void getAll() {
        habitDao.insert(habit1, habit2);
        assertThat(habitDao.getAll(), containsInAnyOrder(habit1, habit2));
    }


}