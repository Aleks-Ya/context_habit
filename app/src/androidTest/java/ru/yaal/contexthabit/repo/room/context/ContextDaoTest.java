package ru.yaal.contexthabit.repo.room.context;

import org.junit.Test;

import ru.yaal.contexthabit.repo.room.BaseAndroidTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ru.yaal.contexthabit.repo.room.TestData.context1;
import static ru.yaal.contexthabit.repo.room.TestData.context2;

public class ContextDaoTest extends BaseAndroidTest {

    @Test
    public void insert() {
        contextDao.insert(context1);
        assertThat(contextDao.getById(context1.id), equalTo(context1));
    }

    @Test
    public void delete() {
        contextDao.insert(context1);
        assertThat(contextDao.getById(context1.id), equalTo(context1));

        contextDao.delete(context1);
        assertNull(contextDao.getById(context1.id));
    }

    @Test
    public void getAll() {
        contextDao.insert(context1, context2);
        assertThat(contextDao.getAll(), containsInAnyOrder(context1, context2));
    }


}