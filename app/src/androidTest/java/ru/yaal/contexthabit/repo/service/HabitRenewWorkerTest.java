package ru.yaal.contexthabit.repo.service;

import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;
import ru.yaal.contexthabit.repo.room.converter.CronConverters;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.ScheduleEntity;
import ru.yaal.contexthabit.service.HabitRenewWorker;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;

public class HabitRenewWorkerTest extends BaseAndroidTest {

    @Test
    public void getNextHabitRenew() throws ExecutionException, InterruptedException {
        ScheduleEntity schedule = new ScheduleEntity();
        CronConverters cronConverters = new CronConverters();
        schedule.cron = cronConverters.fromString("* * * * *");
        schedule.name = "Every second";
        repository.saveSchedule(schedule);

        HabitEntity habit = createHabitNoId1();
        habit.scheduleId = schedule.id;
        repository.saveHabit(habit);

        assertThat(repository.getAllHabitRenews(), empty());

        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(HabitRenewWorker.class).build();
        Operation operation = workManager.enqueue(uploadWorkRequest);
        Operation.State state = operation.getResult().get();
        assertThat(state, Matchers.instanceOf(Operation.State.SUCCESS.class));

        assertThat(repository.getAllHabitRenews(), hasSize(1));
    }
}