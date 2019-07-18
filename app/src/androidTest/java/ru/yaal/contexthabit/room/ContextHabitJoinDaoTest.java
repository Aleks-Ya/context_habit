package ru.yaal.contexthabit.room;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContextHabitJoinDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ContextEntity context = new ContextEntity();
        context.id = 1;
        context.name = "Eat";
        context.parentContextId = ContextEntity.emptyContext.id;
        contextDao.insert(context);

        HabitEntity habit = new HabitEntity();
        habit.id = 2;
        habit.name = "Eat hungry";
        habitDao.insert(habit);

        ContextHabitJoin join = new ContextHabitJoin();
        join.contextId = context.id;
        join.habitId = habit.id;
        contextHabitJoinDao.insert(join);

        List<ContextHabitJoin> allJoins = contextHabitJoinDao.getAll();
        assertThat(allJoins, contains(join));
    }


    @Test
    public void delete() {
        ContextEntity context = new ContextEntity();
        context.id = 1;
        context.name = "Eat";
        context.parentContextId = ContextEntity.emptyContext.id;
        contextDao.insert(context);

        HabitEntity habit = new HabitEntity();
        habit.id = 2;
        habit.name = "Eat hungry";
        habitDao.insert(habit);

        ContextHabitJoin join = new ContextHabitJoin();
        join.contextId = context.id;
        join.habitId = habit.id;
        contextHabitJoinDao.insert(join);

        assertThat(contextHabitJoinDao.getContextsForHabit(habit.id), contains(context));
        assertThat(contextHabitJoinDao.getHabitsForContext(context.id), contains(habit));

        contextHabitJoinDao.delete(join);
        assertThat(contextHabitJoinDao.getContextsForHabit(habit.id), is(empty()));
        assertThat(contextHabitJoinDao.getHabitsForContext(context.id), is(empty()));
    }

    @Test
    public void get() {
        ContextEntity context1 = new ContextEntity();
        context1.id = 1;
        context1.name = "Eat";
        context1.parentContextId = ContextEntity.emptyContext.id;

        ContextEntity context2 = new ContextEntity();
        context2.id = 2;
        context2.name = "Eat Finished";
        context2.parentContextId = context1.id;

        ContextEntity context3 = new ContextEntity();
        context3.id = 3;
        context3.name = "Eat Want";

        contextDao.insert(context1, context2, context3);

        HabitEntity habit1 = new HabitEntity();
        habit1.id = 4;
        habit1.name = "Eat hungry";

        HabitEntity habit2 = new HabitEntity();
        habit2.id = 5;
        habit2.name = "Not eat sugar";

        habitDao.insert(habit1, habit2);

        ContextHabitJoin join1 = new ContextHabitJoin();
        join1.contextId = context1.id;
        join1.habitId = habit1.id;

        ContextHabitJoin join2 = new ContextHabitJoin();
        join2.contextId = context1.id;
        join2.habitId = habit2.id;

        ContextHabitJoin join3 = new ContextHabitJoin();
        join3.contextId = context2.id;
        join3.habitId = habit2.id;

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