drop schema if exists mybnb;

create database mybnb;

use mybnb;

create table acc_occupancy(
    acc_id varchar(10),
    vacancy int default 100,

    primary key (acc_id)
);

create table reservations(
    resv_id varchar(8),
    name varchar(128),
    email varchar(128),
    acc_id varchar(10),
    arrival_date date,
    duration int default 1,

    primary key (resv_id),
    constraint foreign_id foreign key(acc_id) references acc_occupancy(acc_id)
);