insert into AUTHORS (FIRSTNAME, LASTNAME) values ('Лев','Толстой');
insert into AUTHORS (FIRSTNAME, LASTNAME) values ('Ю','Несбё');
insert into AUTHORS (FIRSTNAME, LASTNAME) values ('Уильям','Шекспир');

insert into GENRES (NAME) values ('Роман');
insert into GENRES (NAME) values ('Трагедия');
insert into GENRES (NAME) values ('Детектив');

insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('Война и мир', 1, 1);
insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('Снеговик', 2, 3);
insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('Король лир', 3, 2);
