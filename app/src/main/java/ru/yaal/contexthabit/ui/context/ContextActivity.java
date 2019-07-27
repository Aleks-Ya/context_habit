package ru.yaal.contexthabit.ui.context;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.Repository;
import ru.yaal.contexthabit.repo.RepositoryImpl;
import ru.yaal.contexthabit.repo.room.AppDatabase;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;

import static ru.yaal.contexthabit.repo.room.EntityBuilder.createContext;
import static ru.yaal.contexthabit.repo.room.EntityBuilder.createHabit;
import static ru.yaal.contexthabit.repo.room.context.ContextEntity.emptyContext;

public class ContextActivity extends AppCompatActivity {
    public static final String HABITS_EXTRA_NAME = "habits";
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
                .build();

        repository = new RepositoryImpl(database.contextDao(), database.habitDao(),
                database.contextHabitJoinDao());

        ContextEntity context1 = createContext(1, "Want eat", emptyContext.id);
        ContextEntity context2 = createContext(2, "Leave home", emptyContext.id);
        repository.saveContext(context1, context2);

        HabitEntity habit1 = createHabit(1, "Eat hungry");
        HabitEntity habit2 = createHabit(2, "Bring water everywhere");
        HabitEntity habit3 = createHabit(3, "Not eat 1 hour after workout");
        repository.saveHabit(habit1, habit2, habit3);

        repository.link(context1, habit1);
        repository.link(context2, habit2);
        repository.link(context1, habit3);

        RecyclerView.Adapter mAdapter = new ContextAdapter(repository.getAllContexts());
        contextRecyclerView.setAdapter(mAdapter);
    }
}
