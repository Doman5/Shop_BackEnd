--liquibase formatted sql
--changeset ddomanski:11
alter TABLE cart_item add column cart_id bigint not null;
alter table cart_item add constraint fk_cart_item_cart_id FOREIGN KEY (cart_id) references cart(id);