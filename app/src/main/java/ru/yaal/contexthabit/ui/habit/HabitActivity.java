package ru.yaal.contexthabit.ui.habit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;

import static ru.yaal.contexthabit.ui.context.ContextActivity.CONTEXT_EXTRA_NAME;
import static ru.yaal.contexthabit.ui.context.ContextActivity.HABITS_EXTRA_NAME;

public class HabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);

        RecyclerView recyclerView = findViewById(R.id.habit_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ContextEntity context = (ContextEntity) getIntent().getSerializableExtra(CONTEXT_EXTRA_NAME);
        HabitList habitList = (HabitList) getIntent().getSerializableExtra(HABITS_EXTRA_NAME);

        RecyclerView.Adapter mAdapter = new HabitViewAdapter(context, habitList.getHabits());
        recyclerView.setAdapter(mAdapter);
    }
}
