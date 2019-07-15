package ru.yaal.contexthabit.room;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ActionDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ActionEntity expAction = new ActionEntity();
        expAction.id = 1;
        expAction.contextId = 1;
        expAction.habitId = 1;
        expAction.date = LocalDateTime.now();
        expAction.valueChange = 1;

        actionDao.insert(expAction);

        ActionEntity actAction = actionDao.getById(expAction.id);
        assertThat(actAction, equalTo(expAction));
    }

    @Test
    public void delete() {
        ActionEntity action = new ActionEntity();
        action.id = 2;

        actionDao.insert(action);
        assertThat(actionDao.getById(action.id), equalTo(action));

        actionDao.delete(action);
        assertNull(actionDao.getById(action.id));
    }

    @Test
    public void getAll() {
        ActionEntity action1 = new ActionEntity();
        action1.id = 3;

        ActionEntity action2 = new ActionEntity();
        action2.id = 4;

        actionDao.insert(action1);
        actionDao.insert(action2);
        List<ActionEntity> allContexts = actionDao.getAll();
        assertThat(allContexts, Matchers.containsInAnyOrder(action1, action2));
    }


}