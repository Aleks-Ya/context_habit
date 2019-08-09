package ru.yaal.contexthabit;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.service.Singleton;

public class ContextHabitApp extends Application {

    public void onCreate() {
        super.onCreate();
        Singleton.init(getApplicationContext(), new PopulateDatabaseCallback());
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
