package ru.yaal.contexthabit.repo.service;

import org.junit.Test;

import java.time.LocalDateTime;

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
        LocalDateTime actNextHabitRenew = renewService.getNextHabitRenew(habit);
        LocalDateTime expNextHabitRenew = LocalDateTime.now().plusDays(1)
                .withHour(4).withMinute(0).withSecond(0).withNano(0);
        assertThat(actNextHabitRenew, equalTo(expNextHabitRenew));
    }
}