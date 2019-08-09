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

-- Context 1
insert into ContextEntity(id, name, parentContextId) values (10, 'еда захотелось', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (10, 'Выпил стакан воды', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (11, 'Отложил еду на полчаса', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (12, 'Пишу в дневнике не реже 5 раз в день', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (15, 'Ем 2 ладошки', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (13, 'Кардио-тренировка на голодный желудок', 2, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (14, 'Не ем 1 час после тренировки (силовой или кардио)', 2, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (16, 'Сделал вакуум 3 раза по 10 сек', 1, 5);
insert into ContextHabitJoin(contextId, habitId) values (10,10);
insert into ContextHabitJoin(contextId, habitId) values (10,11);
insert into ContextHabitJoin(contextId, habitId) values (10,12);
insert into ContextHabitJoin(contextId, habitId) values (10,13);
insert into ContextHabitJoin(contextId, habitId) values (10,14);
insert into ContextHabitJoin(contextId, habitId) values (10,15);
insert into ContextHabitJoin(contextId, habitId) values (10,16);

-- Context 2
insert into ContextEntity(id, name, parentContextId) values (11, 'еда начало', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (20, 'Не ем сладости, хлеб и сыр', 1, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (23, 'Прием пищи по чувству физического голода', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (24, 'Не ем по эмоциональному голоду', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (25, 'Выбор продуктов: по желанию', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (26, 'Вставать из-за стола голодным', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (27, 'Вести фото дневник питания', 1, 3);

insert into ContextHabitJoin(contextId, habitId) values (11,20);
insert into ContextHabitJoin(contextId, habitId) values (11,23);
insert into ContextHabitJoin(contextId, habitId) values (11,24);
insert into ContextHabitJoin(contextId, habitId) values (11,25);
insert into ContextHabitJoin(contextId, habitId) values (11,26);
insert into ContextHabitJoin(contextId, habitId) values (11,27);

-- Context 3
insert into ContextEntity(id, name, parentContextId) values (12, 'еда половина', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (30, 'Выбросил остатки еды', 2, 3);
insert into ContextHabitJoin(contextId, habitId) values (12,30);
insert into ContextHabitJoin(contextId, habitId) values (12,26);

-- Context 4
insert into ContextEntity(id, name, parentContextId) values (20, 'дом ухожу', 2);
insert into HabitEntity(id, name, scheduleId, targetValue) values (40, 'Ношу с собой воду', 1, 2);
insert into ContextHabitJoin(contextId, habitId) values (20,40);

-- Context 5
insert into ContextEntity(id, name, parentContextId) values (21, 'дом пришел', 2);
insert into HabitEntity(id, name, scheduleId, targetValue) values (50, 'Принимаю душ 3 раза в день', 1, 3);
insert into ContextHabitJoin(contextId, habitId) values (21,50);

-- Context 6
insert into ContextEntity(id, name, parentContextId) values (22, 'дом проснулся', 2);
insert into HabitEntity(id, name, scheduleId, targetValue) values (60, 'Бег 5 раз в неделю', 2, 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (61, 'Взвесился', 2, 7);
insert into ContextHabitJoin(contextId, habitId) values (22,60);
insert into ContextHabitJoin(contextId, habitId) values (22,61);
insert into ContextHabitJoin(contextId, habitId) values (22,13);

-- Context 7
insert into ContextEntity(id, name, parentContextId) values (70, 'планирование недели', 7);
insert into HabitEntity(id, name, scheduleId, targetValue) values (71, 'Силовая тренировка 3 раза в неделю', 2, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (72, 'Массаж 1 раз в неделю', 2, 1);
insert into ContextHabitJoin(contextId, habitId) values (70,60);
insert into ContextHabitJoin(contextId, habitId) values (70,71);
insert into ContextHabitJoin(contextId, habitId) values (70,72);

-- Context 8
insert into ContextEntity(id, name, parentContextId) values (60, 'тренировка конец', 6);
insert into ContextHabitJoin(contextId, habitId) values (60,14);

-- Context 9
insert into ContextEntity(id, name, parentContextId) values (50, 'Сон ночной ложусь', 5);
insert into HabitEntity(id, name, scheduleId, targetValue) values (90, 'Записал 6 самых важных дел на завтра', 2, 5);
insert into ContextHabitJoin(contextId, habitId) values (50,90);

-- Context 10
insert into ContextEntity(id, name, parentContextId) values (13, 'еда после', 1);
insert into HabitEntity(id, name, scheduleId, targetValue) values (100, 'Ответил на вопросы YouAte', 1, 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (101, 'Принял милдронат 500 гр', 1, 1);
insert into ContextHabitJoin(contextId, habitId) values (13,100);
insert into ContextHabitJoin(contextId, habitId) values (13,101);

-- Context 11
insert into ContextEntity(id, name, parentContextId) values (31, 'кафе выбираю еду', 3);
insert into HabitEntity(id, name, scheduleId, targetValue) values (110, 'Заказал только напиток в кафе', 2, 3);
insert into ContextHabitJoin(contextId, habitId) values (31,110);

-- Context 12
insert into ContextEntity(id, name, parentContextId) values (32, 'кафе ухожу', 3);
insert into ContextHabitJoin(contextId, habitId) values (32,40);

-- Context 13
insert into ContextEntity(id, name, parentContextId) values (40, 'делаю покупки', 4);
insert into ContextHabitJoin(contextId, habitId) values (40,20);
