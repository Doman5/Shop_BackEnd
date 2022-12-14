--liquibase formatted sql
--changeset ddomanski:26
    alter table product add column sale_price decimal(9,2);