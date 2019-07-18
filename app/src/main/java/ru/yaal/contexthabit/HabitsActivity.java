package ru.yaal.contexthabit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.yaal.contexthabit.android.R;

import static ru.yaal.contexthabit.MainActivity.HABITS_EXTRA_NAME;

public class HabitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        RecyclerView recyclerView = findViewById(R.id.habits_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        HabitList habitList = (HabitList) getIntent().getSerializableExtra(HABITS_EXTRA_NAME);

        RecyclerView.Adapter mAdapter = new HabitAdapter(habitList.getHabits());
        recyclerView.setAdapter(mAdapter);
    }
}
