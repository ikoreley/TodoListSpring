CREATE TABLE Task (
                        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        description varchar(100) NOT NULL UNIQUE,
                        status varchar,
                        deadline int NOT NULL
);


INSERT INTO Task (description, status, deadline) VALUES('Прочитать книгу', 'IN_PROGRESS', 1);