--liquibase formatted sql
--changeset ddomanski:13
    alter table cart_item modify column cart_id bigint;