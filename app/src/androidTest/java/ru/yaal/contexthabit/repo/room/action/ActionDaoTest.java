package ru.yaal.contexthabit.repo.room.action;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ActionDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ActionEntity expAction = new ActionEntity();
        expAction.contextId = 1L;
        expAction.habitId = 1L;
        expAction.date = LocalDateTime.now();
        expAction.valueChange = 1;

        long actionId = actionDao.insert(expAction);
        expAction.id = actionId;

        ActionEntity actAction = actionDao.getById(actionId);
        assertThat(actAction, equalTo(expAction));
    }

    @Test
    public void delete() {
        ActionEntity action = new ActionEntity();

        long actionId = actionDao.insert(action);
        action.id = actionId;
        assertThat(actionDao.getById(actionId), equalTo(action));

        actionDao.delete(action);
        assertNull(actionDao.getById(actionId));
    }

    @Test
    public void getAll() {
        ActionEntity action1 = new ActionEntity();
        ActionEntity action2 = new ActionEntity();

        long actionId1 = actionDao.insert(action1);
        long actionId2 = actionDao.insert(action2);

        action1.id = actionId1;
        action2.id = actionId2;

        List<ActionEntity> allContexts = actionDao.getAll();
        assertThat(allContexts, Matchers.containsInAnyOrder(action1, action2));
    }

    @Test
    public void getNegativeValue() {
        long contextId = 1L;
        long habitId = 2L;

        assertThat(actionDao.getNegativeValue(habitId), equalTo(0));

        ActionEntity negAction1 = new ActionEntity();
        negAction1.contextId = contextId;
        negAction1.habitId = habitId;
        negAction1.type = ActionEntity.ActionType.NEGATIVE;
        negAction1.valueChange = -2;
        actionDao.insert(negAction1);
        assertThat(actionDao.getNegativeValue(habitId), equalTo(-2));

        ActionEntity negAction2 = new ActionEntity();
        negAction2.contextId = contextId;
        negAction2.habitId = habitId;
        negAction2.type = ActionEntity.ActionType.NEGATIVE;
        negAction2.valueChange = -1;
        actionDao.insert(negAction2);
        assertThat(actionDao.getNegativeValue(habitId), equalTo(-3));

        ActionEntity posAction = new ActionEntity();
        posAction.contextId = contextId;
        posAction.habitId = habitId;
        posAction.type = ActionEntity.ActionType.POSITIVE;
        posAction.valueChange = -1;
        actionDao.insert(posAction);
        assertThat(actionDao.getNegativeValue(habitId), equalTo(-3));

        ActionEntity anotherContextAction = new ActionEntity();
        anotherContextAction.contextId = contextId + 1;
        anotherContextAction.habitId = habitId;
        anotherContextAction.type = ActionEntity.ActionType.NEGATIVE;
        anotherContextAction.valueChange = -4;
        actionDao.insert(anotherContextAction);
        assertThat(actionDao.getNegativeValue(habitId), equalTo(-7));

        ActionEntity anotherHabitAction = new ActionEntity();
        anotherHabitAction.contextId = contextId;
        anotherHabitAction.habitId = habitId + 1;
        anotherHabitAction.type = ActionEntity.ActionType.NEGATIVE;
        anotherHabitAction.valueChange = -1;
        actionDao.insert(anotherHabitAction);
        assertThat(actionDao.getNegativeValue(habitId), equalTo(-7));
    }


}