package ru.yaal.contexthabit.room;

import android.content.Context;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public abstract class BaseAndroidTest {
    protected ContextDao contextDao;
    protected HabitDao habitDao;
    protected ActionDao actionDao;
    protected ContextHabitJoinDao contextHabitJoinDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        contextDao = db.contextDao();
        habitDao = db.habitDao();
        actionDao = db.actionDao();
        contextHabitJoinDao = db.contextHabitJoinDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

}