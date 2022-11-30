--liquibase formatted sql
--changeset ddomanski:17

alter table orders add column payment_id bigint;
update orders set  orders.payment_id=1;
alter table orders modify payment_id bigint not null ;