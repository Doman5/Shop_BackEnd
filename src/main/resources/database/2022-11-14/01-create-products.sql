--liquibase formatted sql
--changeset ddomanski:1
create table product (
    id bigint not null auto_increment Primary Key,
    name varchar(255) not null,
    category varchar(255) not null,
    description text not null,
    price decimal(9,2) not null,
    currency varchar(255) not null
);