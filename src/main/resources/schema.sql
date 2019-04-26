CREATE USER your_username IDENTIFIED BY your_password;

CREATE TABLE your_username.todo_users(
username VARCHAR2(255),
password VARCHAR2(255),
CONSTRAINT users_pk PRIMARY KEY (username)
);

CREATE TABLE your_username.todos(
todo_id INTEGER,
title VARCHAR2(255),
description VARCHAR2(255),
username VARCHAR2(255),
CONSTRAINT todos_pk PRIMARY KEY (todo_id),
CONSTRAINT todos_user_fk FOREIGN KEY (username) REFERENCES your_username.todo_users(username)
);

GRANT RESOURCE, CONNECT, UNLIMITED TABLESPACE to your_username;

-- Before you can do these steps, you'll need to create the tables
GRANT SELECT, INSERT, UPDATE, DELETE on your_username.todos TO your_username;
GRANT SELECT, INSERT, UPDATE, DELETE on your_username.todo_users TO your_username;



GRANT SELECT, INSERT, UPDATE, DELETE on your_username.todos TO your_username;
GRANT SELECT, INSERT, UPDATE, DELETE on your_username.todo_users TO your_username;

insert into your_username.todo_users VALUES ('user', 'Password123!');
insert into your_username.todo_users VALUES ('admin', 'Password123!');

commit;

CREATE SEQUENCE todos_sequence
    MINVALUE 0
    START WITH 1
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER todos_b_insert 
BEFORE INSERT 
ON your_username.todos
FOR EACH ROW 
BEGIN
    if :new.todo_id IS NULL THEN
        SELECT todos_sequence.nextval INTO :new.todo_id from dual;
    end if;
end;
/

CREATE OR REPLACE PROCEDURE your_username.create_todo(title IN VARCHAR2, 
                                                description IN VARCHAR2, 
                                                username IN VARCHAR2)
AS
BEGIN
    INSERT INTO your_username.todos VALUES (null, title, description, username);
    COMMIT;
END;
/