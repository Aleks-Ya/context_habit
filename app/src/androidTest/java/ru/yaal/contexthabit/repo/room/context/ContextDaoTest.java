package ru.yaal.contexthabit.repo.room.context;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;
import ru.yaal.contexthabit.repo.room.EntityBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId1;
import static ru.yaal.contexthabit.repo.room.TestData.createContextNoId2;
import static ru.yaal.contexthabit.repo.room.context.ContextEntity.emptyContext;

public class ContextDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        ContextEntity context = createContextNoId1();
        long contextId = contextDao.insert(context);
        context.id = contextId;
        assertThat(contextDao.getById(contextId), equalTo(context));
    }

    @Test
    public void delete() {
        ContextEntity context = createContextNoId1();
        long contextId = contextDao.insert(context);
        context.id = contextId;
        assertThat(contextDao.getById(contextId), equalTo(context));

        contextDao.delete(context);
        assertNull(contextDao.getById(contextId));
    }

    @Test
    public void getAll() {
        ContextEntity context1 = createContextNoId1();
        ContextEntity context2 = createContextNoId2();
        long contextId1 = contextDao.insert(context1);
        long contextId2 = contextDao.insert(context2);
        context1.id = contextId1;
        context2.id = contextId2;
        assertThat(contextDao.getAll(), containsInAnyOrder(context1, context2));
    }

    @Test
    public void getRootContexts() {
        ContextEntity context1 = repository.saveContext(
                EntityBuilder.createContext(null, "Eat", emptyContext.id));
        ContextEntity context2 = repository.saveContext(
                EntityBuilder.createContext(null, "Eat", emptyContext.id));
        repository.saveContext(EntityBuilder.createContext(null, "Eat", context1.id));
        assertThat(contextDao.getRootContexts(), containsInAnyOrder(context1, context2));
    }


}