package ru.yaal.contexthabit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.room.AppDatabase;
import ru.yaal.contexthabit.room.ContextDao;
import ru.yaal.contexthabit.room.ContextEntity;
import ru.yaal.contexthabit.room.ContextHabitJoin;
import ru.yaal.contexthabit.room.ContextHabitJoinDao;
import ru.yaal.contexthabit.room.HabitDao;
import ru.yaal.contexthabit.room.HabitEntity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String HABITS_EXTRA_NAME = "habits";
    public static AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.main_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        database = Room.databaseBuilder(this, AppDatabase.class, "context_habit")
                .allowMainThreadQueries()
                .build();


        ContextEntity context1 = new ContextEntity();
        context1.id = 1;
        context1.name = "Want eat";

        ContextEntity context2 = new ContextEntity();
        context2.id = 2;
        context2.name = "Leave home";

        ContextDao contextDao = database.contextDao();
        contextDao.insert(context1);
        contextDao.insert(context2);

        HabitEntity habit1 = new HabitEntity();
        habit1.id = 1;
        habit1.name = "Eat hungry";

        HabitEntity habit2 = new HabitEntity();
        habit2.id = 2;
        habit2.name = "Bring water everywhere";

        HabitEntity habit3 = new HabitEntity();
        habit3.id = 3;
        habit3.name = "Not eat 1 hour after workout";

        HabitDao habitDao = database.habitDao();
        habitDao.insert(habit1, habit2, habit3);

        ContextHabitJoin join1 = new ContextHabitJoin();
        join1.contextId = context1.id;
        join1.habitId = habit1.id;

        ContextHabitJoin join2 = new ContextHabitJoin();
        join2.contextId = context2.id;
        join2.habitId = habit2.id;

        ContextHabitJoin join3 = new ContextHabitJoin();
        join3.contextId = context1.id;
        join3.habitId = habit3.id;

        ContextHabitJoinDao contextHabitJoinDao = database.contextHabitJoinDao();
        contextHabitJoinDao.insert(join1, join2, join3);

        List<ContextEntity> contexts = contextDao.getAll();
        RecyclerView.Adapter mAdapter = new ContextAdapter(contexts);
        recyclerView.setAdapter(mAdapter);
    }

//    public void showHabits(View view) {
//        Intent intent = new Intent(this, HabitsActivity.class);
//        intent.putExtra(HABITS_EXTRA_NAME, );
//        startActivity(intent);
//    }

}
