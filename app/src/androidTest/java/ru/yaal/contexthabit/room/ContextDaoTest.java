package ru.yaal.contexthabit.room;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ContextDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ContextEntity expContext = new ContextEntity();
        expContext.id = 1;
        expContext.name = "Eat";
        expContext.parentContextId = null;

        contextDao.insert(expContext);

        ContextEntity actContext = contextDao.getById(expContext.id);
        assertThat(actContext, equalTo(expContext));
    }

    @Test
    public void delete() {
        ContextEntity context = new ContextEntity();
        context.id = 2;

        contextDao.insert(context);
        assertThat(contextDao.getById(context.id), equalTo(context));

        contextDao.delete(context);
        assertNull(contextDao.getById(context.id));
    }

    @Test
    public void getAll() {
        ContextEntity expContext1 = new ContextEntity();
        expContext1.id = 3;
        expContext1.name = "Eat";
        expContext1.parentContextId = ContextEntity.emptyContext.id;

        ContextEntity expContext2 = new ContextEntity();
        expContext2.id = 4;
        expContext2.name = "Eat Finished";
        expContext2.parentContextId = expContext1.id;

        contextDao.insert(expContext1);
        contextDao.insert(expContext2);
        List<ContextEntity> allContexts = contextDao.getAll();
        assertThat(allContexts, Matchers.containsInAnyOrder(expContext1, expContext2));
    }


}