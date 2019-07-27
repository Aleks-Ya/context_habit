package ru.yaal.contexthabit.repo.room.converter;

import androidx.room.TypeConverter;

import ru.yaal.contexthabit.repo.room.action.ActionEntity;

public class ActionTypeConverters {

    @TypeConverter
    public ActionEntity.ActionType fromString(String value) {
        if (value == null) {
            return null;
        }
        return ActionEntity.ActionType.valueOf(value);
    }

    @TypeConverter
    public String toString(ActionEntity.ActionType actionType) {
        if (actionType == null) {
            return null;
        }
        return actionType.toString();
    }
}
