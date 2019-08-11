insert into ScheduleEntity(id, name, cron) values (1, 'Ежедневно', '0 4 * * *');
insert into ScheduleEntity(id, name, cron) values (2, 'Еженедельно', '0 4 * * 1');

-- Root contexts
insert into ContextEntity(id, name, parentContextId) values (1, 'еда', 0);
insert into ContextEntity(id, name, parentContextId) values (2, 'дом', 0);
insert into ContextEntity(id, name, parentContextId) values (3, 'кафе', 0);
insert into ContextEntity(id, name, parentContextId) values (4, 'магазин', 0);
insert into ContextEntity(id, name, parentContextId) values (5, 'сон', 0);
insert into ContextEntity(id, name, parentContextId) values (6, 'тренировка', 0);
insert into ContextEntity(id, name, parentContextId) values (7, 'планирование', 0);
insert into ContextEntity(id, name, parentContextId) values (8, 'эмоция', 0);

-- Context 10
insert into ContextEntity(id, name, parentContextId) values (10, 'захотелось есть', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (10, 'Выпил стакан воды', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (11, 'Сделал вакуум 3 раза по 10 сек', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (12, 'Отложил еду на полчаса', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (13, 'Пишу в дневнике не реже 5 раз в день', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (14, 'Ем 2 ладошки', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (15, 'Кардио-тренировка на голодный желудок', 2, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (16, 'Не ем 1 час после тренировки (силовой или кардио)', 2, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (17, 'Прогулялся по улице', 1, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 11, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 10, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 12, 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 13, 4);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 14, 5);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 17, 6);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 15, 7);
insert into ContextHabitJoin(contextId, habitId, "order") values (10, 16, 8);

-- Context 11
insert into ContextEntity(id, name, parentContextId) values (11, 'начал есть', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (20, 'Вести фото дневник питания', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (21, 'Ем без девайсов (свидание с едой)', 1, 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (22, 'Не ем сладости, хлеб и сыр', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (23, 'Прием пищи по чувству физического голода', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (24, 'Выбор продуктов: по желанию', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (25, 'Встал из-за стола голодным', 1, 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (11, 20, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (11, 21, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (11, 22, 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (11, 23, 4);
insert into ContextHabitJoin(contextId, habitId, "order") values (11, 24, 5);
insert into ContextHabitJoin(contextId, habitId, "order") values (11, 25, 6);

-- Context 12
insert into ContextEntity(id, name, parentContextId) values (12, 'съел половину (почувствовал насыщение)', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (30, 'Выбросил остатки еды', 2, 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (12, 30, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (12, 25, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (12, 26, 3);

-- Context 13
insert into ContextEntity(id, name, parentContextId) values (13, 'после еды', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (100, 'Ответил на вопросы YouAte', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (101, 'Принял милдронат 500 гр', 1, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (13, 100, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (13, 21, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (13, 101, 3);

-- Context 20
insert into ContextEntity(id, name, parentContextId) values (20, 'ухожу на улицу', 2);
insert into HabitEntity(id, name, scheduleId, targetValue) values (40, 'Ношу с собой воду', 1, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (20, 40, 1);

-- Context 21
insert into ContextEntity(id, name, parentContextId) values (21, 'вернулся домой', 2);
insert into HabitEntity(id, name, scheduleId, targetValue) values (50, 'Принимаю душ 3 раза в день', 1, 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (21, 50, 1);

-- Context 31
insert into ContextEntity(id, name, parentContextId) values (31, 'выбираю блюда', 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (110, 'Заказал только напиток в кафе', 2, 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (31, 110, 1);

-- Context 32
insert into ContextEntity(id, name, parentContextId) values (32, 'ухожу из кафе', 3);
insert into ContextHabitJoin(contextId, habitId, "order") values (32, 40, 1);

-- Context 40
insert into ContextEntity(id, name, parentContextId) values (40, 'делаю покупки', 4);
insert into HabitEntity(id, name, scheduleId, targetValue) values (41, 'Не покупаю сладости, хлеб и сыр', 1, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (40, 41, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (40, 22, 2);

-- Context 50
insert into ContextEntity(id, name, parentContextId) values (50, 'Проснулся утром', 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (60, 'Бег 5 раз в неделю', 2, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (61, 'Взвесился', 2, 7);
insert into ContextHabitJoin(contextId, habitId, "order") values (50, 60, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (50, 61, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (50, 13, 3);

-- Context 52
insert into ContextEntity(id, name, parentContextId) values (52, 'Собираюсь спать', 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (520, 'Выпил 3 ложки масла', 1, 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (521, 'Почистил зубы пастой', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (522, 'Почистил зубы нитью', 1, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (52, 520, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (52, 521, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (52, 522, 3);

-- Context 51
insert into ContextEntity(id, name, parentContextId) values (53, 'Ложусь спать (ночь)', 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (90, 'Записал 6 самых важных дел на завтра', 2, 5);
insert into ContextHabitJoin(contextId, habitId, "order") values (53, 90, 1);

-- Context 60
insert into ContextEntity(id, name, parentContextId) values (60, 'после тренировки', 6);
insert into ContextHabitJoin(contextId, habitId, "order") values (60, 16, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (60, 71, 1);

-- Context 70
insert into ContextEntity(id, name, parentContextId) values (70, 'планирую неделю', 7);
insert into HabitEntity(id, name, scheduleId, targetValue) values (71, 'Силовая тренировка 3 раза в неделю', 2, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (72, 'Массаж 1 раз в неделю', 2, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (70, 60, 1);
insert into ContextHabitJoin(contextId, habitId, "order") values (70, 71, 2);
insert into ContextHabitJoin(contextId, habitId, "order") values (70, 72, 3);

-- Context 80
insert into ContextEntity(id, name, parentContextId) values (80, 'Скука', 8);

-- Context 90
insert into ContextEntity(id, name, parentContextId) values (90, 'Стресс', 8);

-- Context 100
insert into ContextEntity(id, name, parentContextId) values (100, 'Одиночество', 8);
