package ru.yaal.contexthabit.repo.room;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import androidx.work.testing.SynchronousExecutor;
import androidx.work.testing.WorkManagerTestInitHelper;

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
import ru.yaal.contexthabit.service.Singleton;

@RunWith(AndroidJUnit4ClassRunner.class)
public abstract class BaseAndroidTest {
    protected Context context;
    protected ContextDao contextDao;
    protected HabitDao habitDao;
    protected ActionDao actionDao;
    protected ContextHabitJoinDao contextHabitJoinDao;
    protected ScheduleDao scheduleDao;
    protected Repository repository;
    protected RenewService renewService;
    protected HabitRenewDao habitRenewDao;
    protected WorkManager workManager;
    private AppDatabase db;

    @Before
    public void createDb() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
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
        workManager = initWorkManager(context);
        Singleton.repository = repository;
        Singleton.renewService = renewService;
    }

    private static WorkManager initWorkManager(Context context) {
        Configuration config = new Configuration.Builder()
                .setMinimumLoggingLevel(Log.DEBUG)
                .setExecutor(new SynchronousExecutor())
                .build();
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config);
        return WorkManager.getInstance(context);
    }

    @After
    public void closeDb() {
        db.close();
    }

}