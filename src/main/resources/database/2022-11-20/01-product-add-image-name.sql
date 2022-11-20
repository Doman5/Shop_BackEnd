--liquibase formatted sql
--changeset ddomanski:2
alter table product add column image varchar(128) after currency;