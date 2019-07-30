package ru.yaal.contexthabit.repo.room.habit;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitRenewNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdDaily;

public class HabitRenewDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        repository.saveSchedule(createScheduleNoIdDaily());
        repository.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity = createHabitRenewNoId1();
        long habitRenewId = habitRenewDao.insert(habitRenewEntity);
        habitRenewEntity.id = habitRenewId;
        HabitRenewEntity actHabitRenew = habitRenewDao.getById(habitRenewId);
        assertThat(actHabitRenew, equalTo(habitRenewEntity));
    }

    @Test
    public void delete() {
        repository.saveSchedule(createScheduleNoIdDaily());
        repository.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity = repository.saveHabitRenew(createHabitRenewNoId1());
        assertThat(habitRenewDao.getById(habitRenewEntity.id), equalTo(habitRenewEntity));

        habitRenewDao.delete(habitRenewEntity);
        assertThat(habitRenewDao.getById(habitRenewEntity.id), nullValue());
    }

    @Test
    public void getAll() {
        repository.saveSchedule(createScheduleNoIdDaily());
        repository.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity1 = repository.saveHabitRenew(createHabitRenewNoId1());
        HabitRenewEntity habitRenewEntity2 = repository.saveHabitRenew(createHabitRenewNoId1());
        assertThat(habitRenewDao.getAll(), containsInAnyOrder(habitRenewEntity1, habitRenewEntity2));
    }
}