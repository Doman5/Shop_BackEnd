--liquibase formatted sql
--changeset ddomanski:10
create table cart(
    id bigint not null auto_increment primary key,
    created datetime not null
);
create table cart_item(
    id bigint not null auto_increment primary key,
    product_id bigint not null,
    quantity int,
    constraint fk_cart_item_product_id foreign key (product_id) references product(id)
);