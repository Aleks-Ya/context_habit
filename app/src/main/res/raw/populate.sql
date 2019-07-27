
-- Context 1
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (1, 'еда захотелось', 0);
INSERT INTO HabitEntity(id, name) VALUES (10, 'Пишу в дневнике не реже 5 раз в день');
INSERT INTO HabitEntity(id, name) VALUES (11, 'Кардио-тренировка на голодный желудок');
INSERT INTO HabitEntity(id, name) VALUES (12, 'Не ем 1 час после тренировки (силовой или кардио)');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (1,10);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (1,11);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (1,12);

-- Context 2
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (2, 'еда начало', 0);
INSERT INTO HabitEntity(id, name) VALUES (20, 'Не ем сладости, хлеб и сыр');
--INSERT INTO HabitEntity(id, name) VALUES (21, 'Не ем 1 час после тренировки (силовой или кардио)');
--INSERT INTO HabitEntity(id, name) VALUES (22, 'Кардио-тренировка на голодный желудок');
INSERT INTO HabitEntity(id, name) VALUES (23, 'Прием пищи по чувству физического голода');
INSERT INTO HabitEntity(id, name) VALUES (24, 'Не ем по эмоциональному голоду');
INSERT INTO HabitEntity(id, name) VALUES (25, 'Выбор продуктов: по желанию');
INSERT INTO HabitEntity(id, name) VALUES (26, 'Вставать из-за стола голодным');
INSERT INTO HabitEntity(id, name) VALUES (27, 'Вести фото дневник питания');

INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,20);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,12);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,11);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,23);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,24);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,25);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,26);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (2,27);

-- Context 3
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (3, 'еда половина', 0);
--INSERT INTO HabitEntity(id, name) VALUES (30, 'Вставать из-за стола голодным');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (3,26);

-- Context 4
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (4, 'дом ухожу', 0);
INSERT INTO HabitEntity(id, name) VALUES (40, 'Ношу с собой воду');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (4,40);

-- Context 5
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (5, 'дом пришел', 0);
INSERT INTO HabitEntity(id, name) VALUES (50, 'Принимаю душ 3 раза в день');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (5,50);

-- Context 6
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (6, 'дом проснулся', 0);
INSERT INTO HabitEntity(id, name) VALUES (60, 'Бег 5 раз в неделю');
--INSERT INTO HabitEntity(id, name) VALUES (61, 'Кардио-тренировка на голодный желудок');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (6,60);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (6,11);

-- Context 7
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (7, 'планирование недели', 0);
--INSERT INTO HabitEntity(id, name) VALUES (70, 'Бег 5 раз в неделю');
INSERT INTO HabitEntity(id, name) VALUES (71, 'Силовая тренировка 3 раза в неделю');
INSERT INTO HabitEntity(id, name) VALUES (72, 'Массаж 1 раз в неделю');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (7,60);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (7,71);
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (7,72);

-- Context 8
INSERT INTO ContextEntity(id, name, parentContextId) VALUES (8, 'тренировка конец', 0);
--INSERT INTO HabitEntity(id, name) VALUES (80, 'Не ем 1 час после тренировки (силовой или кардио)');
INSERT INTO ContextHabitJoin(contextId, habitId) VALUES (8,12);
