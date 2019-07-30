package ru.yaal.contexthabit.repo.room;

import android.content.Context;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import ru.yaal.contexthabit.repo.Repository;
import ru.yaal.contexthabit.repo.RepositoryImpl;
import ru.yaal.contexthabit.repo.room.action.ActionDao;
import ru.yaal.contexthabit.repo.room.context.ContextDao;
import ru.yaal.contexthabit.repo.room.context.ContextHabitJoinDao;
import ru.yaal.contexthabit.repo.room.habit.HabitDao;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewDao;
import ru.yaal.contexthabit.repo.room.habit.ScheduleDao;
import ru.yaal.contexthabit.service.RenewService;
import ru.yaal.contexthabit.service.RenewServiceImpl;

@RunWith(AndroidJUnit4ClassRunner.class)
public abstract class BaseAndroidTest {
    protected ContextDao contextDao;
    protected HabitDao habitDao;
    protected ActionDao actionDao;
    protected ContextHabitJoinDao contextHabitJoinDao;
    protected ScheduleDao scheduleDao;
    protected Repository repository;
    protected RenewService renewService;
    protected HabitRenewDao habitRenewDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        contextDao = db.contextDao();
        habitDao = db.habitDao();
        actionDao = db.actionDao();
        contextHabitJoinDao = db.contextHabitJoinDao();
        scheduleDao = db.scheduleDao();
        habitRenewDao = db.habitRenewDao();
        repository = new RepositoryImpl(contextDao, habitDao, contextHabitJoinDao, actionDao,
                scheduleDao, habitRenewDao);
        renewService = new RenewServiceImpl(repository);
    }

    @After
    public void closeDb() {
        db.close();
    }

}