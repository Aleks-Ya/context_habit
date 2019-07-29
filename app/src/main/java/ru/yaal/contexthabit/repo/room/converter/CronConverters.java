package ru.yaal.contexthabit.repo.room.converter;

import androidx.room.TypeConverter;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;

import static com.cronutils.model.CronType.UNIX;

public class CronConverters {
    private static CronParser parser;

    @TypeConverter
    public Cron fromString(String value) {
        if (value == null) {
            return null;
        }
        initParser();
        return parser.parse(value);
    }

    @TypeConverter
    public String toString(Cron cron) {
        if (cron == null) {
            return null;
        }
        initParser();
        return cron.asString();
    }

    /* Initialization in constructor and in static field doesn't work */
    private static void initParser() {
        if (parser == null) {
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(UNIX);
            parser = new CronParser(cronDefinition);
        }
    }
}
