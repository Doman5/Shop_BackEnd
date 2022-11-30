--liquibase formatted sql
--changeset ddomanski:16

create table payment(
    id bigint not null auto_increment primary key ,
    name varchar(64) not null ,
    type varchar(32) not null ,
    default_payment boolean default false,
    note text
);

insert into payment(id, name, type, default_payment, note)
VALUES (1, 'Przelew bankowy', 'BANK_TRANSFER', true, 'Prosimy o dokonanie przelewu na konto:\n 30 2000 1000 4000 3000 2000 1000\n w tytule proszę podać nr zamówienia');