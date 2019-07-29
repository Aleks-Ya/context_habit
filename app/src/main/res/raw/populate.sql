insert into ScheduleEntity(id, name, cron) values (1, 'Ежедневно', '0 4 * * *');
insert into ScheduleEntity(id, name, cron) values (2, 'Еженедельно', '0 4 * * 1');


-- Context 1
insert into ContextEntity(id, name, parentContextId) values (1, 'еда захотелось', 0);
insert into HabitEntity(id, name) values (10, 'Пишу в дневнике не реже 5 раз в день');
insert into HabitEntity(id, name) values (11, 'Кардио-тренировка на голодный желудок');
insert into HabitEntity(id, name) values (12, 'Не ем 1 час после тренировки (силовой или кардио)');
insert into ContextHabitJoin(contextId, habitId) values (1,10);
insert into ContextHabitJoin(contextId, habitId) values (1,11);
insert into ContextHabitJoin(contextId, habitId) values (1,12);

-- Context 2
insert into ContextEntity(id, name, parentContextId) values (2, 'еда начало', 0);
insert into HabitEntity(id, name) values (20, 'Не ем сладости, хлеб и сыр');
--INSERT INTO HabitEntity(id, name) VALUES (21, 'Не ем 1 час после тренировки (силовой или кардио)');
--INSERT INTO HabitEntity(id, name) VALUES (22, 'Кардио-тренировка на голодный желудок');
insert into HabitEntity(id, name) values (23, 'Прием пищи по чувству физического голода');
insert into HabitEntity(id, name) values (24, 'Не ем по эмоциональному голоду');
insert into HabitEntity(id, name) values (25, 'Выбор продуктов: по желанию');
insert into HabitEntity(id, name) values (26, 'Вставать из-за стола голодным');
insert into HabitEntity(id, name) values (27, 'Вести фото дневник питания');

insert into ContextHabitJoin(contextId, habitId) values (2,20);
insert into ContextHabitJoin(contextId, habitId) values (2,12);
insert into ContextHabitJoin(contextId, habitId) values (2,11);
insert into ContextHabitJoin(contextId, habitId) values (2,23);
insert into ContextHabitJoin(contextId, habitId) values (2,24);
insert into ContextHabitJoin(contextId, habitId) values (2,25);
insert into ContextHabitJoin(contextId, habitId) values (2,26);
insert into ContextHabitJoin(contextId, habitId) values (2,27);

-- Context 3
insert into ContextEntity(id, name, parentContextId) values (3, 'еда половина', 0);
--INSERT INTO HabitEntity(id, name) VALUES (30, 'Вставать из-за стола голодным');
insert into ContextHabitJoin(contextId, habitId) values (3,26);

-- Context 4
insert into ContextEntity(id, name, parentContextId) values (4, 'дом ухожу', 0);
insert into HabitEntity(id, name) values (40, 'Ношу с собой воду');
insert into ContextHabitJoin(contextId, habitId) values (4,40);

-- Context 5
insert into ContextEntity(id, name, parentContextId) values (5, 'дом пришел', 0);
insert into HabitEntity(id, name) values (50, 'Принимаю душ 3 раза в день');
insert into ContextHabitJoin(contextId, habitId) values (5,50);

-- Context 6
insert into ContextEntity(id, name, parentContextId) values (6, 'дом проснулся', 0);
insert into HabitEntity(id, name) values (60, 'Бег 5 раз в неделю');
--INSERT INTO HabitEntity(id, name) VALUES (61, 'Кардио-тренировка на голодный желудок');
insert into ContextHabitJoin(contextId, habitId) values (6,60);
insert into ContextHabitJoin(contextId, habitId) values (6,11);

-- Context 7
insert into ContextEntity(id, name, parentContextId) values (7, 'планирование недели', 0);
--INSERT INTO HabitEntity(id, name) VALUES (70, 'Бег 5 раз в неделю');
insert into HabitEntity(id, name) values (71, 'Силовая тренировка 3 раза в неделю');
insert into HabitEntity(id, name) values (72, 'Массаж 1 раз в неделю');
insert into ContextHabitJoin(contextId, habitId) values (7,60);
insert into ContextHabitJoin(contextId, habitId) values (7,71);
insert into ContextHabitJoin(contextId, habitId) values (7,72);

-- Context 8
insert into ContextEntity(id, name, parentContextId) values (8, 'тренировка конец', 0);
--INSERT INTO HabitEntity(id, name) VALUES (80, 'Не ем 1 час после тренировки (силовой или кардио)');
insert into ContextHabitJoin(contextId, habitId) values (8,12);
