--liquibase formatted sql
--changeset ddomanski:4
alter table product add column full_description text not null after description;