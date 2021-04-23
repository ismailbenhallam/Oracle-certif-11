create table persons
(
    id         int auto_increment primary key,
    first_name varchar(15) not null,
    last_name  varchar(15) not null
);

create
unique index users_id_uindex
    on persons (id);

-- Insert some data
INSERT INTO persons
VALUES (NULL, 'Ismaïl', 'BENHALLAM'),
       (NULL, 'Yassin', 'LAHMAR'),
       (NULL, 'Salma', 'BENHALLAM'),
       (NULL, 'Sara', 'BENHALLAM')