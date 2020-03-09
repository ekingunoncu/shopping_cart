CREATE DATABASE shopping_cart;

-- USER DBA

CREATE ROLE "DBA" WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	REPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'Ab123123';

-- USER APP

CREATE USER application WITH ENCRYPTED PASSWORD 'Ab123123';
GRANT ALL PRIVILEGES ON DATABASE shopping_cart TO application;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA dev TO application;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA dev TO application;

-- PRODUCT_TABLE

create table PRODUCT_TABLE
(
	PRODUCT_ID BIGINT not null,
	PRODUCT_TITLE VARCHAR(400),
	PRODUCT_PRICE DECIMAL,
	CATEGORY_ID BIGINT
);

create unique index PRODUCT_TABLE_PRODUCT_ID_uindex
	on PRODUCT_TABLE (PRODUCT_ID);

alter table PRODUCT_TABLE
	add constraint PRODUCT_TABLE_pk
		primary key (PRODUCT_ID);


-- CAMPAIGN_TABLE

create table CAMPAIGN_TABLE
(
	CAMPAIGN_ID BIGINT not null,
	DISCOUNT DECIMAL not null,
	CATEGORY_ID BIGINT,
	MINIMUM_QUANTITY INTEGER,
	DISCOUNT_TYPE VARCHAR(10)
);

create unique index CAMPAIGN_TABLE_CAMPAIGN_ID_uindex
	on CAMPAIGN_TABLE (CAMPAIGN_ID);

alter table CAMPAIGN_TABLE
	add constraint CAMPAIGN_TABLE_pk
		primary key (CAMPAIGN_ID);

-- CART TABLE

create table CART_TABLE
(
	CART_ID BIGINT not null,
	USER_ID BIGINT not null
);

create unique index CART_TABLE_CART_ID_uindex
	on CART_TABLE (CART_ID);

create unique index cart_table_user_id_uindex
    on cart_table (user_id);

alter table CART_TABLE
	add constraint CART_TABLE_pk
		primary key (CART_ID);

-- CART_ITEM_TABLE

create table CART_ITEM_TABLE
(
	CART_ITEM_ID BIGINT not null,
	CART_ID BIGINT not null,
	PRODUCT_ID BIGINT not null,
	QUANTITY INTEGER not null,
	DISCOUNT DECIMAL not null,
	PRICE DECIMAL not null,
    CATEGORY_ID  BIGINT
);

create unique index CART_ITEM_TABLE_CART_ITEM_ID_uindex
	on CART_ITEM_TABLE (CART_ITEM_ID);

alter table CART_ITEM_TABLE
	add constraint CART_ITEM_TABLE_pk
		primary key (CART_ITEM_ID);

-- CATEGORY_TABLE

create table CATEGORY_TABLE
(
	CATEGORY_ID BIGINT not null,
	CATEGORY_PARENT_ID BIGINT,
	CATEGORY_TITLE VARCHAR(400) not null
);

create unique index CATEGORY_TABLE_CATEGORY_ID_uindex
	on CATEGORY_TABLE (CATEGORY_ID);

alter table CATEGORY_TABLE
	add constraint CATEGORY_TABLE_pk
		primary key (CATEGORY_ID);



-- COUPON_TABLE

create table COUPON_TABLE
(
	COUPON_ID BIGINT not null,
	MINIMUM_AMOUNT DECIMAL not null,
	DISCOUNT DECIMAL not null,
	DISCOUNT_TYPE VARCHAR(10)
);

create unique index COUPON_TABLE_COUPON_ID_uindex
	on COUPON_TABLE (COUPON_ID);

alter table COUPON_TABLE
	add constraint COUPON_TABLE_pk
		primary key (COUPON_ID);

-- FK SETTINGS

alter table campaign_table
	add constraint campaign_table_category_table_category_id_fk
		foreign key (category_id) references category_table;

alter table cart_item_table
	add constraint cart_item_table_cart_table_cart_id_fk
		foreign key (cart_id) references cart_table;

alter table cart_item_table
	add constraint cart_item_table_product_table_product_id_fk
		foreign key (product_id) references product_table;

alter table product_table
	add constraint product_table_category_table_category_id_fk
		foreign key (category_id) references category_table;

-- HIBERNATE SEQ

CREATE SEQUENCE hibernate_sequence START 1;