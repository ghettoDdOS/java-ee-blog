INSERT INTO author (full_name, email, created_at)
VALUES ('Иванов Петр Григорьевич', 'ivanov.petr@example.com', TIMESTAMP '2023-05-01 10:00:00');
INSERT INTO author (full_name, email, created_at)
VALUES ('Смирнова Ольга Викторовна', 'smirnova.olga@example.com', TIMESTAMP '2023-05-02 14:30:00');
INSERT INTO author (full_name, email, created_at)
VALUES ('Козлов Андрей Александрович', 'kozlov.andrey@example.com', TIMESTAMP '2023-05-03 20:45:00');

INSERT INTO blog (title, content, created_at, id_author)
VALUES ('Новый пост', 'Это текст нового поста', TIMESTAMP '2023-05-01 08:00:00', 1);
INSERT INTO blog (title, content, created_at, id_author)
VALUES ('Заголовок поста', 'Это содержание поста', TIMESTAMP '2023-05-02 13:15:00', 2);
INSERT INTO blog (title, content, created_at, id_author)
VALUES ('Размышления', 'Содержание поста на тему размышлений', TIMESTAMP '2023-05-03 21:30:00', 3);
INSERT INTO blog (title, content, created_at, id_author)
VALUES ('Новости дня', 'Это новости дня, пожалуйста, оставайтесь на связи', TIMESTAMP '2023-05-04 09:45:00', 3);
