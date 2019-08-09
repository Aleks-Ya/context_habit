package ru.yaal.contexthabit.ui.context;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;

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

        ContextEntity context = (ContextEntity) getIntent().getSerializableExtra(CONTEXT_EXTRA_NAME);
        if (context == null) {
            context = ContextEntity.emptyContext;
        }

        List<ContextEntity> contexts = repository.getNestedContexts(context);
        RecyclerView.Adapter mAdapter = new ContextViewAdapter(contexts);
        contextRecyclerView.setAdapter(mAdapter);
    }
}
