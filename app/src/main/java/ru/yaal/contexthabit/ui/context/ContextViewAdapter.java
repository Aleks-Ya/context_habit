package ru.yaal.contexthabit.ui.context;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.ui.habit.HabitActivity;

import static ru.yaal.contexthabit.ui.context.ContextActivity.CONTEXT_EXTRA_NAME;

public class ContextViewAdapter extends RecyclerView.Adapter<ContextViewAdapter.ContextViewHolder> {
    private List<ContextEntity> contexts;

    static class ContextViewHolder extends RecyclerView.ViewHolder {

        ContextViewHolder(View view) {
            super(view);
        }
    }

    ContextViewAdapter(List<ContextEntity> contexts) {
        this.contexts = contexts;
    }

    @NonNull
    @Override
    public ContextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflated = inflater.inflate(R.layout.view_context, parent, false);
        return new ContextViewAdapter.ContextViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(@NonNull ContextViewHolder holder, int position) {
        ContextEntity context = contexts.get(position);

        ContextView contextView = (ContextView) holder.itemView;
        Button contextButton = contextView.getContextButton();

        ContextActivity contextActivity = (ContextActivity) contextView.getContext();

        ContextViewModel model = new ContextViewModel();
        model.contextEntity.observe(contextActivity, new ContextButtonObserver(contextButton));

        model.contextEntity.setValue(context);

        contextButton.setOnClickListener(new ContextButtonOnClickListener(model));
    }

    @Override
    public int getItemCount() {
        return contexts.size();
    }

    public static class ContextButtonOnClickListener implements View.OnClickListener {
        private final ContextViewModel model;

        ContextButtonOnClickListener(ContextViewModel model) {
            this.model = model;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), HabitActivity.class);
            intent.putExtra(CONTEXT_EXTRA_NAME, model.contextEntity.getValue());
            view.getContext().startActivity(intent);
        }
    }
}

