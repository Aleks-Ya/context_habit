package ru.yaal.contexthabit.repo.service;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdDaily;

public class RenewServiceImplTest extends BaseAndroidTest {

    @Test
    public void getNextHabitRenew() {
        repository.saveSchedule(createScheduleNoIdDaily());
        HabitEntity habit = repository.saveHabit(createHabitNoId1());
        LocalDateTime localDateTimeNow = LocalDateTime.of(2019, 10, 21, 22, 35);
        ZonedDateTime now = localDateTimeNow.atZone(ZoneId.systemDefault());
        LocalDateTime actNextHabitRenew = renewService.getNextHabitRenew(habit, now);
        LocalDateTime expNextHabitRenew = localDateTimeNow.plusDays(1)
                .withHour(4).withMinute(0).withSecond(0).withNano(0);
        assertThat(actNextHabitRenew, equalTo(expNextHabitRenew));
    }
}