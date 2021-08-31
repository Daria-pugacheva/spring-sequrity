create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

-- create table roles
-- (
--     id   serial,
--     name varchar(50) not null,
--     primary key (id)
-- );
--
-- CREATE TABLE users_roles
-- (
--     user_id bigint not null,
--     role_id int    not null,
--     primary key (user_id, role_id),
--     foreign key (user_id) references users (id),
--     foreign key (role_id) references roles (id)
-- );

create table authorities
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_authorities
(
    user_id      bigint not null,
    authority_id int    not null,
    primary key (user_id, authority_id),
    foreign key (user_id) references users (id),
    foreign key (authority_id) references authorities (id)
);

-- insert into roles (name)
-- values ('ROLE_USER'),
--        ('ROLE_ADMIN');

insert into authorities (name)
values ('EDIT_PRODUCT'),
       ('COMPLETE_ORDER'),
       ('ADMINISTRATE');
--
-- insert into users (username, password, email)
-- values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users (username, password, email)
values ('client', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),     --пароль 100
       ('manager', '$2a$12$tcNWfma1eoVToqptAKfR2.RUs0zg1qk8wssGhdvRLYMyHivN01466', 'manager@gmail.com'), --пароль 200
       ('admin', '$2a$12$tnWEnpq9RGnTEq4TXxO2DuJwE26CSgIaCihFUCiCSWlHmdF9CUiQu', 'admin@gmail.com'),     --пароль 300
       ('guest', '$2a$12$uaSEYwO4S0wyER4IlnOXKOaImgbLDi/mB3McO9xgXpv4E.DVk0NfG', 'guest@gmail.com'); --пароль 400 - с его правами и наличие БД пароль особо не нужен (чисто инфо посмотреть для проверки)


-- insert into users_roles (user_id, role_id)
-- values
-- (1, 1),
-- (1, 2);

insert into users_authorities (user_id, authority_id)
values (1, 2),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3);