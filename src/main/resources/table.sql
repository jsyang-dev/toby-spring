create table users (
    id varchar(20) primary key,
    name varchar(20) not null,
    password varchar(20) not null
);

alter table users add level tinyint not null;
alter table users add login int not null;
alter table users add recommend int not null;
alter table users add email varchar(320) not null;