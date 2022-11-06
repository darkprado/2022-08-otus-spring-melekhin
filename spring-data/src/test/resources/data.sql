insert into AUTHORS (FIRSTNAME, LASTNAME) values ('A1','A1');
insert into AUTHORS (FIRSTNAME, LASTNAME) values ('A2','A2');
insert into AUTHORS (FIRSTNAME, LASTNAME) values ('A3','A3');

insert into GENRES (NAME) values ('G1');
insert into GENRES (NAME) values ('G2');
insert into GENRES (NAME) values ('G3');

insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('B1', 1, 1);
insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('B2', 2, 3);
insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values ('B3', 3, 2);

insert into COMMENTS (COMMENT_TEXT, BOOK_ID) values ('C1', 1);
insert into COMMENTS (COMMENT_TEXT, BOOK_ID) values ('C2', 1);
insert into COMMENTS (COMMENT_TEXT, BOOK_ID) values ('C3', 2);
insert into COMMENTS (COMMENT_TEXT, BOOK_ID) values ('C4', 3);
