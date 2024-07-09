create table if not exists "user" (
    id bigint not null auto_increment,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    phone varchar(255),
    user_role varchar(255),
    primary key (id)


);