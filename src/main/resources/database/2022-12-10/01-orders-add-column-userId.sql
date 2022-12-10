--liquibase formatted sql
--changeset ddomanski:22
    alter table orders add column user_id bigint;
--changeset ddomasnki:23
    alter table orders add constraint fk_orders_user_id foreign key (user_id) references users(id);