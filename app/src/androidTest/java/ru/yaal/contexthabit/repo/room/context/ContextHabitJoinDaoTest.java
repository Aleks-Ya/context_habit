package ru.yaal.contexthabit.repo.room.context;

import org.junit.Test;

import java.util.List;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createContextHabitJoin;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId2;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId3;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createHabitNoId2;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdDaily;
import static ru.yaal.contexthabit.repo.room.TestData.createScheduleNoIdWeekly;

public class ContextHabitJoinDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ContextEntity context = createContextNoId1();
        long contextId = contextDao.insert(context);
        context.id = contextId;

        repo.saveSchedule(createScheduleNoIdDaily());
        HabitEntity habit = createHabitNoId1();
        long habitId = habitDao.insert(habit);
        habit.id = habitId;

        ContextHabitJoin join = createContextHabitJoin(contextId, habitId);
        contextHabitJoinDao.insert(join);

        assertThat(contextHabitJoinDao.getAll(), contains(join));
    }


    @Test
    public void delete() {
        ContextEntity context = createContextNoId1();
        long contextId = contextDao.insert(context);
        context.id = contextId;

        repo.saveSchedule(createScheduleNoIdDaily());
        HabitEntity habit = createHabitNoId1();
        long habitId = habitDao.insert(habit);
        habit.id = habitId;

        ContextHabitJoin join = createContextHabitJoin(contextId, habitId);
        contextHabitJoinDao.insert(join);

        assertThat(contextHabitJoinDao.getContextsForHabit(habitId), contains(context));
        assertThat(contextHabitJoinDao.getHabitsForContext(contextId), contains(habit));

        contextHabitJoinDao.delete(join);
        assertThat(contextHabitJoinDao.getContextsForHabit(habitId), is(empty()));
        assertThat(contextHabitJoinDao.getHabitsForContext(contextId), is(empty()));
    }

    @Test
    public void get() {
        ContextEntity context1 = createContextNoId1();
        ContextEntity context2 = createContextNoId2();
        ContextEntity context3 = createContextNoId3();
        long contextId1 = contextDao.insert(context1);
        long contextId2 = contextDao.insert(context2);
        long contextId3 = contextDao.insert(context3);
        context1.id = contextId1;
        context2.id = contextId2;
        context3.id = contextId3;
        repo.saveSchedule(createScheduleNoIdDaily());
        repo.saveSchedule(createScheduleNoIdWeekly());
        HabitEntity habit1 = createHabitNoId1();
        HabitEntity habit2 = createHabitNoId2();
        long habitId1 = habitDao.insert(habit1);
        long habitId2 = habitDao.insert(habit2);
        habit1.id = habitId1;
        habit2.id = habitId2;

        ContextHabitJoin join1 = createContextHabitJoin(contextId1, habitId1);
        ContextHabitJoin join2 = createContextHabitJoin(contextId1, habitId2);
        ContextHabitJoin join3 = createContextHabitJoin(contextId2, habitId2);
        contextHabitJoinDao.insert(join1, join2, join3);

        List<ContextEntity> habit1Contexts = contextHabitJoinDao.getContextsForHabit(habitId1);
        assertThat(habit1Contexts, containsInAnyOrder(context1));

        List<ContextEntity> habit2Contexts = contextHabitJoinDao.getContextsForHabit(habitId2);
        assertThat(habit2Contexts, containsInAnyOrder(context1, context2));

        List<HabitEntity> context1Habits = contextHabitJoinDao.getHabitsForContext(contextId1);
        assertThat(context1Habits, containsInAnyOrder(habit1, habit2));

        List<HabitEntity> context2Habits = contextHabitJoinDao.getHabitsForContext(contextId2);
        assertThat(context2Habits, containsInAnyOrder(habit2));

        List<HabitEntity> context3Habits = contextHabitJoinDao.getHabitsForContext(contextId3);
        assertThat(context3Habits, is(empty()));
    }


}