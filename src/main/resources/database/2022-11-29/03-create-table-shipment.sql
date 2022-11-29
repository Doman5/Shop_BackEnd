--liquibase formatted sql
--changeset ddomanski:14

create table shipment(
    id bigint not null auto_increment primary key ,
    name varchar(64) not null ,
    price decimal(6,2) not null ,
    type varchar(32) not null ,
    default_shipment boolean default false
);

insert into shipment(name, price, type, default_shipment) VALUES ('kurier', 14.99, 'DELIVERYMAN', true),
                                                                     ('Odbiór osobisty', 0.0, 'SELF_PICKUP', false);