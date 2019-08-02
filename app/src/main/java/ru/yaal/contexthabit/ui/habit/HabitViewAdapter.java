package ru.yaal.contexthabit.ui.habit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;

import ru.yaal.contexthabit.android.R;
import ru.yaal.contexthabit.repo.room.action.ActionEntity;
import ru.yaal.contexthabit.repo.room.context.ContextEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitEntity;
import ru.yaal.contexthabit.repo.room.habit.HabitRenewEntity;

import static ru.yaal.contexthabit.repo.room.action.ActionEntity.ActionType.NEGATIVE;
import static ru.yaal.contexthabit.repo.room.action.ActionEntity.ActionType.POSITIVE;
import static ru.yaal.contexthabit.service.Singleton.renewService;
import static ru.yaal.contexthabit.service.Singleton.repository;

public class HabitViewAdapter extends RecyclerView.Adapter<HabitViewAdapter.HabitViewHolder> {
    private ContextEntity context;
    private List<HabitEntity> habits;

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        HabitViewHolder(View view) {
            super(view);
        }
    }

    HabitViewAdapter(ContextEntity context, List<HabitEntity> habits) {
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflated = inflater.inflate(R.layout.view_habit, parent, false);
        return new HabitViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        HabitView habitView = (HabitView) holder.itemView;
        HabitActivity habitActivity = (HabitActivity) habitView.getContext();

        HabitViewModel model = new HabitViewModel();

        TextView habitNameTextView = habitView.getHabitNameTextView();
        HabitNameObserver habitNameObserver = new HabitNameObserver(habitNameTextView);
        model.habitEntity.observe(habitActivity, habitNameObserver);

        Button negativeMinusButton = habitView.getNegativeMinusButton();
        Button negativePlusButton = habitView.getNegativePlusButton();
        Button positiveMinusButton = habitView.getPositiveMinusButton();
        Button positivePlusButton = habitView.getPositivePlusButton();

        TextView negativeValueTextView = habitView.getNegativeValueTextView();
        TextView positiveValueTextView = habitView.getPositiveValueTextView();
        TextView nextRenewTextView = habitView.getNextRenewTextView();

        model.negativeCount.observe(habitActivity,
                new ValueButtonObserver(negativeValueTextView, negativeMinusButton));
        model.positiveCount.observe(habitActivity,
                new ValueButtonObserver(positiveValueTextView, positiveMinusButton));

        addButtonListener(negativeMinusButton, model, -1, NEGATIVE);
        addButtonListener(negativePlusButton, model, 1, NEGATIVE);
        addButtonListener(positiveMinusButton, model, -1, POSITIVE);
        addButtonListener(positivePlusButton, model, 1, POSITIVE);

        model.nextRenew.observe(habitActivity, new NextRenewObserver(nextRenewTextView));

        ProgressBar progressBar = habitView.getProgressBar();
        TextView progressTextView = habitView.getProgressTextView();
        model.progress.observe(habitActivity, new ProgressBarValueObserver(progressBar));
        model.progressStr.observe(habitActivity, new ProgressTextViewObserver(progressTextView));
        model.habitEntity.observe(habitActivity, new ProgressBarHabitObserver(progressBar));

        HabitEntity habit = habits.get(position);
        HabitRenewEntity lastHabitRenew = repository.getLastHabitRenew(habit);
        LocalDateTime lastRenewDate = lastHabitRenew != null ? lastHabitRenew.date : LocalDateTime.now();
        int positiveValue = repository.getPositiveValue(habit.id, lastRenewDate);
        int negativeValue = repository.getNegativeValue(habit.id, lastRenewDate);
        model.contextEntity.setValue(context);
        model.habitEntity.setValue(habit);
        model.negativeCount.setValue(negativeValue);
        model.positiveCount.setValue(positiveValue);
        model.nextRenew.setValue(renewService.getNextHabitRenew(habit, ZonedDateTime.now()));
        model.progress.setValue(positiveValue - negativeValue);
        model.progressStr.setValue(formatProgressStr(positiveValue - negativeValue, habit.targetValue));
    }

    private void addButtonListener(Button button, HabitViewModel model, int valueChange,
                                   ActionEntity.ActionType type) {
        ButtonOnClickListener listener = new ButtonOnClickListener(model, valueChange, type);
        button.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    private static class ButtonOnClickListener implements View.OnClickListener {
        private final HabitViewModel model;
        private final int valueChange;
        private final ActionEntity.ActionType type;

        ButtonOnClickListener(HabitViewModel model, int valueChange, ActionEntity.ActionType type) {
            this.model = model;
            this.valueChange = valueChange;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            ActionEntity actionEntity = new ActionEntity();
            ContextEntity contextEntity = model.contextEntity.getValue();
            actionEntity.contextId = contextEntity != null ? contextEntity.id : null;
            HabitEntity habitEntity = model.habitEntity.getValue();
            actionEntity.habitId = habitEntity != null ? habitEntity.id : null;
            actionEntity.date = LocalDateTime.now();
            actionEntity.valueChange = valueChange;
            actionEntity.type = type;

            repository.saveAction(actionEntity);

            switch (type) {
                case POSITIVE:
                    updateValue(model.positiveCount, valueChange);
                    break;
                case NEGATIVE:
                    updateValue(model.negativeCount, valueChange);
                    break;
                default:
                    throw new RuntimeException("Unknown ActionType: " + type);
            }

            updateProgress(model);
        }

        private static void updateProgress(HabitViewModel model) {
            Integer positiveCount = model.positiveCount.getValue();
            int posCount = positiveCount != null ? positiveCount : 0;
            Integer negativeCount = model.negativeCount.getValue();
            int negCount = negativeCount != null ? negativeCount : 0;
            Integer progress = posCount - negCount;
            model.progress.setValue(progress);

            HabitEntity targetValue = model.habitEntity.getValue();
            int tarValue = targetValue != null ? targetValue.targetValue : 0;
            String progressStr = formatProgressStr(progress, tarValue);
            model.progressStr.setValue(progressStr);
        }

        private void updateValue(MutableLiveData<Integer> liveData, int valueChange) {
            Integer value = liveData.getValue();
            value = value != null ? value : 0;
            int newValue = value + valueChange;
            liveData.setValue(newValue);
        }
    }

    private static String formatProgressStr(Integer progress, int tarValue) {
        return String.format(Locale.getDefault(), "%d/%d", progress, tarValue);
    }
}
