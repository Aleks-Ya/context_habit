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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.Repository;
import ru.yaal.contexthabit.repo.RepositoryImpl;
import ru.yaal.contexthabit.repo.room.AppDatabase;

public class ContextActivity extends AppCompatActivity {
    public static final String HABITS_EXTRA_NAME = "habits";
    public static final String CONTEXT_EXTRA_NAME = "context";
    public static Repository repository;

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
                database.contextHabitJoinDao(), database.actionDao());

        RecyclerView.Adapter mAdapter = new ContextAdapter(repository.getAllContexts());
        contextRecyclerView.setAdapter(mAdapter);
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
