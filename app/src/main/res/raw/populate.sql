insert into ScheduleEntity(id, name, cron) values (1, 'Ежедневно', '0 4 * * *');
insert into ScheduleEntity(id, name, cron) values (2, 'Еженедельно', '0 4 * * 1');


-- Context 1
insert into ContextEntity(id, name, parentContextId) values (1, 'еда захотелось', 0);
insert into HabitEntity(id, name, scheduleId) values (10, 'Выпил стакан воды', 1);
insert into HabitEntity(id, name, scheduleId) values (11, 'Отложил еду на полчаса', 1);
insert into HabitEntity(id, name, scheduleId) values (12, 'Пишу в дневнике не реже 5 раз в день', 1);
insert into HabitEntity(id, name, scheduleId) values (15, 'Ем 2 ладошки', 1);
insert into HabitEntity(id, name, scheduleId) values (13, 'Кардио-тренировка на голодный желудок', 1);
insert into HabitEntity(id, name, scheduleId) values (14, 'Не ем 1 час после тренировки (силовой или кардио)', 1);
insert into ContextHabitJoin(contextId, habitId) values (1,10);
insert into ContextHabitJoin(contextId, habitId) values (1,11);
insert into ContextHabitJoin(contextId, habitId) values (1,12);
insert into ContextHabitJoin(contextId, habitId) values (1,13);
insert into ContextHabitJoin(contextId, habitId) values (1,14);
insert into ContextHabitJoin(contextId, habitId) values (1,15);

-- Context 2
insert into ContextEntity(id, name, parentContextId) values (2, 'еда начало', 0);
insert into HabitEntity(id, name, scheduleId) values (20, 'Не ем сладости, хлеб и сыр', 1);
insert into HabitEntity(id, name, scheduleId) values (23, 'Прием пищи по чувству физического голода', 1);
insert into HabitEntity(id, name, scheduleId) values (24, 'Не ем по эмоциональному голоду', 1);
insert into HabitEntity(id, name, scheduleId) values (25, 'Выбор продуктов: по желанию', 1);
insert into HabitEntity(id, name, scheduleId) values (26, 'Вставать из-за стола голодным', 1);
insert into HabitEntity(id, name, scheduleId) values (27, 'Вести фото дневник питания', 1);

insert into ContextHabitJoin(contextId, habitId) values (2,20);
insert into ContextHabitJoin(contextId, habitId) values (2,23);
insert into ContextHabitJoin(contextId, habitId) values (2,24);
insert into ContextHabitJoin(contextId, habitId) values (2,25);
insert into ContextHabitJoin(contextId, habitId) values (2,26);
insert into ContextHabitJoin(contextId, habitId) values (2,27);

-- Context 3
insert into ContextEntity(id, name, parentContextId) values (3, 'еда половина', 0);
insert into HabitEntity(id, name, scheduleId) values (30, 'Выбросил остатки еды', 2);
insert into ContextHabitJoin(contextId, habitId) values (3,30);
insert into ContextHabitJoin(contextId, habitId) values (3,26);

-- Context 4
insert into ContextEntity(id, name, parentContextId) values (4, 'дом ухожу', 0);
insert into HabitEntity(id, name, scheduleId) values (40, 'Ношу с собой воду', 1);
insert into ContextHabitJoin(contextId, habitId) values (4,40);

-- Context 5
insert into ContextEntity(id, name, parentContextId) values (5, 'дом пришел', 0);
insert into HabitEntity(id, name, scheduleId) values (50, 'Принимаю душ 3 раза в день', 1);
insert into ContextHabitJoin(contextId, habitId) values (5,50);

-- Context 6
insert into ContextEntity(id, name, parentContextId) values (6, 'дом проснулся', 0);
insert into HabitEntity(id, name, scheduleId) values (60, 'Бег 5 раз в неделю', 2);
insert into HabitEntity(id, name, scheduleId) values (61, 'Взвесился', 2);
insert into ContextHabitJoin(contextId, habitId) values (6,60);
insert into ContextHabitJoin(contextId, habitId) values (6,61);
insert into ContextHabitJoin(contextId, habitId) values (6,13);

-- Context 7
insert into ContextEntity(id, name, parentContextId) values (7, 'планирование недели', 0);
insert into HabitEntity(id, name, scheduleId) values (71, 'Силовая тренировка 3 раза в неделю', 2);
insert into HabitEntity(id, name, scheduleId) values (72, 'Массаж 1 раз в неделю', 2);
insert into ContextHabitJoin(contextId, habitId) values (7,60);
insert into ContextHabitJoin(contextId, habitId) values (7,71);
insert into ContextHabitJoin(contextId, habitId) values (7,72);

-- Context 8
insert into ContextEntity(id, name, parentContextId) values (8, 'тренировка конец', 0);
insert into ContextHabitJoin(contextId, habitId) values (8,14);

-- Context 9
insert into ContextEntity(id, name, parentContextId) values (9, 'Сон ночной ложусь', 0);
insert into HabitEntity(id, name, scheduleId) values (90, 'Записал 6 самых важных дел на завтра', 2);
insert into ContextHabitJoin(contextId, habitId) values (9,90);

-- Context 10
insert into ContextEntity(id, name, parentContextId) values (10, 'еда после', 0);
insert into HabitEntity(id, name, scheduleId) values (100, 'Ответил на вопросы YouAte', 1);
insert into ContextHabitJoin(contextId, habitId) values (10,100);

-- Context 11
insert into ContextEntity(id, name, parentContextId) values (11, 'кафе выбираю еду', 0);
