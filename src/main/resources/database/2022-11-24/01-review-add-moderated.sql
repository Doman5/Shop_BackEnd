--liquibase formatted sql
--changeset ddomanski:9
alter table review add column moderated boolean default false;