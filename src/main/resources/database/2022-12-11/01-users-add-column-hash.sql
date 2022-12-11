--liquibase formatted sql
--changeset ddomanski:24
    alter table users add column hash varchar(120);
--changeset ddomanski:25
    alter table users add column hash_date datetime;