package ru.yaal.contexthabit.repo;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId2;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId2;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdDaily;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdWeekly;

public class RepositoryImplTest extends BaseAndroidTest {

    @Test
    public void saveContext() {
        assertThat(repo.getAllContexts(), is(empty()));

        ContextEntity context1 = repo.saveContext(createContextNoId1());
        ContextEntity context2 = repo.saveContext(createContextNoId2());
        assertThat(repo.getAllContexts(), containsInAnyOrder(context1, context2));
    }

    @Test
    public void saveHabit() {
        assertThat(habitDao.getAll(), is(empty()));

        repo.saveSchedule(createScheduleNoIdDaily());
        repo.saveSchedule(createScheduleNoIdWeekly());
        HabitEntity habit1 = repo.saveHabit(createHabitNoId1());
        HabitEntity habit2 = repo.saveHabit(createHabitNoId2());
        assertThat(habitDao.getAll(), containsInAnyOrder(habit1, habit2));
    }

    @Test
    public void link() {
        repo.saveSchedule(createScheduleNoIdDaily());
        repo.saveSchedule(createScheduleNoIdWeekly());

        ContextEntity context1 = repo.saveContext(createContextNoId1());
        HabitEntity habit1 = repo.saveHabit(createHabitNoId1());
        HabitEntity habit2 = repo.saveHabit(createHabitNoId2());

        assertThat(repo.getHabitsForContext(context1), is(empty()));

        repo.link(context1, habit1);
        repo.link(context1, habit2);

        assertThat(repo.getHabitsForContext(context1), containsInAnyOrder(habit1, habit2));
    }
}