package ru.yaal.contexthabit.repo.room.habit;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createScheduleEntity;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdDaily;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdWeekly;

public class ScheduleDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ScheduleEntity schedule = createScheduleEntity(null, "Daily", "0 4 * * *");
        long scheduleId = scheduleDao.insert(schedule);
        schedule.id = scheduleId;
        assertThat(scheduleDao.getById(scheduleId), equalTo(schedule));
    }

    @Test
    public void delete() {
        ScheduleEntity schedule = createScheduleNoIdDaily();
        long scheduleId = scheduleDao.insert(schedule);
        schedule.id = scheduleId;
        assertThat(scheduleDao.getById(scheduleId), equalTo(schedule));

        scheduleDao.delete(schedule);
        assertNull(scheduleDao.getById(scheduleId));
    }

    @Test
    public void getAll() {
        ScheduleEntity schedule1 = createScheduleNoIdDaily();
        schedule1.id = scheduleDao.insert(schedule1);

        ScheduleEntity schedule2 = createScheduleNoIdWeekly();
        schedule2.id = scheduleDao.insert(schedule2);

        assertThat(scheduleDao.getAll(), containsInAnyOrder(schedule1, schedule2));
    }


}