package ru.yaal.contexthabit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.room.AppDatabase;
import ru.yaal.contexthabit.room.ContextDao;
import ru.yaal.contexthabit.room.ContextEntity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.main_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "context_habit")
                .allowMainThreadQueries()
                .build();
        ContextDao contextDao = database.contextDao();

        ContextEntity context1 = new ContextEntity();
        context1.id = 1;
        context1.name = "My first context";

        ContextEntity context2 = new ContextEntity();
        context2.id = 2;
        context2.name = "My second context";

        contextDao.insert(context1, context2);

        List<ContextEntity> contexts = contextDao.getAll();
        RecyclerView.Adapter mAdapter = new ContextAdapter(contexts);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

}
