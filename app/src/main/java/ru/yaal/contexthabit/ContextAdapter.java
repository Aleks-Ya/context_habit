package ru.yaal.contexthabit;

import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.yaal.contexthabit.room.ContextEntity;

public class ContextAdapter extends RecyclerView.Adapter<ContextAdapter.ContextViewHolder> {
    private List<ContextEntity> dataset;

    public static class ContextViewHolder extends RecyclerView.ViewHolder {
        public Button view;

        public ContextViewHolder(Button view) {
            super(view);
            this.view = view;
        }
    }

    public ContextAdapter(List<ContextEntity> dataset) {
        this.dataset = dataset;
    }

    @Override
    public ContextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button button = new Button(parent.getContext());
        return new ContextViewHolder(button);
    }

    @Override
    public void onBindViewHolder(ContextViewHolder holder, int position) {
        holder.view.setText(dataset.get(position).name);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

