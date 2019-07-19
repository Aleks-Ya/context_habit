package ru.yaal.contexthabit.repo.room;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createContextHabitJoin;
import static ru.yaal.contexthabit.repo.room.TestData.context1;
import static ru.yaal.contexthabit.repo.room.TestData.context2;
import static ru.yaal.contexthabit.repo.room.TestData.context3;
import static ru.yaal.contexthabit.repo.room.TestData.habit1;
import static ru.yaal.contexthabit.repo.room.TestData.habit2;

public class ContextHabitJoinDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        contextDao.insert(context1);
        habitDao.insert(habit1);

        ContextHabitJoin join = createContextHabitJoin(context1.id, habit1.id);
        contextHabitJoinDao.insert(join);

        assertThat(contextHabitJoinDao.getAll(), contains(join));
    }


    @Test
    public void delete() {
        contextDao.insert(context1);
        habitDao.insert(habit1);

        ContextHabitJoin join = createContextHabitJoin(context1.id, habit1.id);
        contextHabitJoinDao.insert(join);

        assertThat(contextHabitJoinDao.getContextsForHabit(habit1.id), contains(context1));
        assertThat(contextHabitJoinDao.getHabitsForContext(context1.id), contains(habit1));

        contextHabitJoinDao.delete(join);
        assertThat(contextHabitJoinDao.getContextsForHabit(habit1.id), is(empty()));
        assertThat(contextHabitJoinDao.getHabitsForContext(context1.id), is(empty()));
    }

    @Test
    public void get() {
        contextDao.insert(context1, context2, context3);
        habitDao.insert(habit1, habit2);

        ContextHabitJoin join1 = createContextHabitJoin(context1.id, habit1.id);
        ContextHabitJoin join2 = createContextHabitJoin(context1.id, habit2.id);
        ContextHabitJoin join3 = createContextHabitJoin(context2.id, habit2.id);
        contextHabitJoinDao.insert(join1, join2, join3);

        List<ContextEntity> habit1Contexts = contextHabitJoinDao.getContextsForHabit(habit1.id);
        assertThat(habit1Contexts, containsInAnyOrder(context1));

        List<ContextEntity> habit2Contexts = contextHabitJoinDao.getContextsForHabit(habit2.id);
        assertThat(habit2Contexts, containsInAnyOrder(context1, context2));

        List<HabitEntity> context1Habits = contextHabitJoinDao.getHabitsForContext(context1.id);
        assertThat(context1Habits, containsInAnyOrder(habit1, habit2));

        List<HabitEntity> context2Habits = contextHabitJoinDao.getHabitsForContext(context2.id);
        assertThat(context2Habits, containsInAnyOrder(habit2));

        List<HabitEntity> context3Habits = contextHabitJoinDao.getHabitsForContext(context3.id);
        assertThat(context3Habits, is(empty()));
    }


}