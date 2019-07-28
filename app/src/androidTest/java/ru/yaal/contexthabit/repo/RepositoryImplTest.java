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

public class RepositoryImplTest extends BaseAndroidTest {

    @Test
    public void saveContext() {
        assertThat(repository.getAllContexts(), is(empty()));

        ContextEntity context1 = repository.saveContext(createContextNoId1());
        ContextEntity context2 = repository.saveContext(createContextNoId2());
        assertThat(repository.getAllContexts(), containsInAnyOrder(context1, context2));
    }

    @Test
    public void saveHabit() {
        assertThat(habitDao.getAll(), is(empty()));
        HabitEntity habit1 = repository.saveHabit(createHabitNoId1());
        HabitEntity habit2 = repository.saveHabit(createHabitNoId2());
        assertThat(habitDao.getAll(), containsInAnyOrder(habit1, habit2));
    }

    @Test
    public void link() {
        ContextEntity context1 = repository.saveContext(createContextNoId1());
        HabitEntity habit1 = repository.saveHabit(createHabitNoId1());
        HabitEntity habit2 = repository.saveHabit(createHabitNoId2());

        assertThat(repository.getHabitsForContext(context1), is(empty()));

        repository.link(context1, habit1);
        repository.link(context1, habit2);

        assertThat(repository.getHabitsForContext(context1), containsInAnyOrder(habit1, habit2));
    }
}