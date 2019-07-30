package ru.yaal.contexthabit.ui.context;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import ru.yaal.contexthabit.repo.RepositoryImpl;
import ru.yaal.contexthabit.repo.room.AppDatabase;
import ru.yaal.contexthabit.service.HabitRenewWorker;
import ru.yaal.contexthabit.service.RenewServiceImpl;
import ru.yaal.contexthabit.service.Singleton;

import static ru.yaal.contexthabit.service.Singleton.repository;

public class ContextActivity extends AppCompatActivity {
    public static final String HABITS_EXTRA_NAME = "habits";
    public static final String CONTEXT_EXTRA_NAME = "context";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        RecyclerView contextRecyclerView = findViewById(R.id.context_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contextRecyclerView.setLayoutManager(layoutManager);

        AppDatabase database = Room.databaseBuilder(
                this, AppDatabase.class, "context_habit")
                .allowMainThreadQueries()
                .addCallback(new PopulateDatabaseCallback())
                .build();

        repository = new RepositoryImpl(database.contextDao(), database.habitDao(),
                database.contextHabitJoinDao(), database.actionDao(), database.scheduleDao(),
                database.habitRenewDao());

        Singleton.renewService = new RenewServiceImpl(repository);

        RecyclerView.Adapter mAdapter = new ContextAdapter(repository.getAllContexts());
        contextRecyclerView.setAdapter(mAdapter);

        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        runHabitRenewWorker(workManager);
    }

    private void runHabitRenewWorker(WorkManager workManager) {
        PeriodicWorkRequest request = new PeriodicWorkRequest
                .Builder(HabitRenewWorker.class, 1, TimeUnit.MINUTES)
                .build();
        workManager.enqueue(request);
    }

    private class PopulateDatabaseCallback extends RoomDatabase.Callback {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i("Database", "Populating database");
            try {
                InputStream is = getResources().openRawResource(R.raw.populate);
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
}
