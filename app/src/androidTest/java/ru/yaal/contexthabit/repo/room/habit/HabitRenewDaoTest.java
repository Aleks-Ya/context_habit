package ru.yaal.contexthabit.repo.room.habit;

import org.junit.Test;

import java.time.LocalDateTime;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createHabitRenewEntity;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitRenewNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdDaily;

public class HabitRenewDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        repo.saveSchedule(createScheduleNoIdDaily());
        repo.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity = createHabitRenewNoId1();
        long habitRenewId = habitRenewDao.insert(habitRenewEntity);
        habitRenewEntity.id = habitRenewId;
        HabitRenewEntity actHabitRenew = habitRenewDao.getById(habitRenewId);
        assertThat(actHabitRenew, equalTo(habitRenewEntity));
    }

    @Test
    public void delete() {
        repo.saveSchedule(createScheduleNoIdDaily());
        repo.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity = repo.saveHabitRenew(createHabitRenewNoId1());
        assertThat(habitRenewDao.getById(habitRenewEntity.id), equalTo(habitRenewEntity));

        habitRenewDao.delete(habitRenewEntity);
        assertThat(habitRenewDao.getById(habitRenewEntity.id), nullValue());
    }

    @Test
    public void getAll() {
        repo.saveSchedule(createScheduleNoIdDaily());
        repo.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity1 = repo.saveHabitRenew(createHabitRenewNoId1());
        HabitRenewEntity habitRenewEntity2 = repo.saveHabitRenew(createHabitRenewNoId1());
        assertThat(habitRenewDao.getAll(), containsInAnyOrder(habitRenewEntity1, habitRenewEntity2));
    }

    @Test
    public void getLastRenewForHabit() {
        ScheduleEntity schedule = repo.saveSchedule(createScheduleNoIdDaily());
        HabitEntity habit = repo.saveHabit(createHabitNoId1());
        HabitRenewEntity habitRenewEntity1 = repo.saveHabitRenew(
                createHabitRenewEntity(habit.id, schedule.id, LocalDateTime.of(2019, 10, 21, 22, 35)));
        repo.saveHabitRenew(
                createHabitRenewEntity(habit.id, schedule.id, LocalDateTime.of(2018, 10, 21, 22, 35)));
        HabitRenewEntity lastRenewForHabit = habitRenewDao.getLastRenewForHabit(habit.id);
        assertThat(lastRenewForHabit, equalTo(habitRenewEntity1));
    }
}