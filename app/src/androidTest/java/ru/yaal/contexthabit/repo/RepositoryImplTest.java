package ru.yaal.contexthabit.repo;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.context1;
import static ru.yaal.contexthabit.repo.room.TestData.context2;
import static ru.yaal.contexthabit.repo.room.TestData.habit1;
import static ru.yaal.contexthabit.repo.room.TestData.habit2;

public class RepositoryImplTest extends BaseAndroidTest {

    @Test
    public void saveContext() {
        assertThat(repository.getAllContexts(), is(empty()));
        repository.saveContext(context1, context2);
        assertThat(repository.getAllContexts(), containsInAnyOrder(context1, context2));
    }

    @Test
    public void saveHabit() {
        assertThat(habitDao.getAll(), is(empty()));
        repository.saveHabit(habit1, habit2);
        assertThat(habitDao.getAll(), containsInAnyOrder(habit1, habit2));
    }

    @Test
    public void link() {
        repository.saveContext(context1);
        repository.saveHabit(habit1, habit2);

        assertThat(repository.getHabitsForContext(context1), is(empty()));

        repository.link(context1, habit1);
        repository.link(context1, habit2);

        assertThat(repository.getHabitsForContext(context1), containsInAnyOrder(habit1, habit2));
    }
}