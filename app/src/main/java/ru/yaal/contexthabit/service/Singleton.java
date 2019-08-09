package ru.yaal.contexthabit.service;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.Repository;
import ru.yaal.contexthabit.repo.RepositoryImpl;
import ru.yaal.contexthabit.repo.room.AppDatabase;

public final class Singleton {
    public static Repository repository;
    public static RenewService renewService;

    public static void init(Context context) {
        AppDatabase database = Room.databaseBuilder(
                context, AppDatabase.class, "context_habit")
                .allowMainThreadQueries()
                .addCallback(new PopulateDatabaseCallback())
                .build();

        repository = new RepositoryImpl(database.contextDao(), database.habitDao(),
                database.contextHabitJoinDao(), database.actionDao(), database.scheduleDao(),
                database.habitRenewDao());

        renewService = new RenewServiceImpl(repository);

        WorkManager workManager = WorkManager.getInstance(context);
        runHabitRenewWorker(workManager);
    }

    private static class PopulateDatabaseCallback extends RoomDatabase.Callback {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i("Database", "Populating database");
            try {
                InputStream is = Resources.getSystem().openRawResource(R.raw.populate);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader buffer = new BufferedReader(isr);
                String line;
                while ((line = buffer.readLine()) != null) {
                    if (line.isEmpty() || line.startsWith("--")) {
                        continue;
                    }
                    db.execSQL(line);
                }
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    private static void runHabitRenewWorker(WorkManager workManager) {
        PeriodicWorkRequest request = new PeriodicWorkRequest
                .Builder(HabitRenewWorker.class, 1, TimeUnit.MINUTES)
                .build();
        workManager.enqueue(request);
    }
}
