package ru.yaal.contexthabit.ui.context;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.yaal.contexthabit.android.R;

import static ru.yaal.contexthabit.service.Singleton.repository;

public class ContextActivity extends AppCompatActivity {
    public static final String CONTEXT_EXTRA_NAME = "context";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        RecyclerView contextRecyclerView = findViewById(R.id.context_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contextRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new ContextViewAdapter(repository.getAllContexts());
        contextRecyclerView.setAdapter(mAdapter);
    }
}
