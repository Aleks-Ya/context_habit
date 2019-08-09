package ru.yaal.contexthabit;

import android.app.Application;

import ru.yaal.contexthabit.service.Singleton;

public class ContextHabitApp extends Application {
    public void onCreate() {
        super.onCreate();
        Singleton.init(getApplicationContext());
    }
}
