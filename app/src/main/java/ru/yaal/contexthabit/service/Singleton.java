package ru.yaal.contexthabit.service;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

import ru.yaal.contexthabit.repo.Repository;
import ru.yaal.contexthabit.repo.RepositoryImpl;
import ru.yaal.contexthabit.repo.room.AppDatabase;

public final class Singleton {
    public static Repository repository;
    public static RenewService renewService;

    public static void init(Context context, RoomDatabase.Callback populateDatabaseCallback) {
        AppDatabase database = Room.databaseBuilder(
                context, AppDatabase.class, "context_habit")
                .allowMainThreadQueries()
                .addCallback(populateDatabaseCallback)
                .build();

        repository = new RepositoryImpl(database.contextDao(), database.habitDao(),
                database.contextHabitJoinDao(), database.actionDao(), database.scheduleDao(),
                database.habitRenewDao());

        renewService = new RenewServiceImpl(repository);

        WorkManager workManager = WorkManager.getInstance(context);
        runHabitRenewWorker(workManager);
    }

    private static void runHabitRenewWorker(WorkManager workManager) {
        PeriodicWorkRequest request = new PeriodicWorkRequest
                .Builder(HabitRenewWorker.class, 1, TimeUnit.MINUTES)
                .build();
        workManager.enqueue(request);
    }
}
