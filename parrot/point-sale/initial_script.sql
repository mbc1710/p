CREATE SCHEMA point_sale;

CREATE SEQUENCE point_sale.user_id_seq
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE point_sale.order_id_seq
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE point_sale.product_id_seq
NO MINVALUE
NO MAXVALUE;

SET search_path to point_sale;

CREATE TABLE "point_sale"."users" (
	user_id int4 NOT NULL DEFAULT nextval('point_sale.user_id_seq'::text::regclass),
	email varchar(80) NOT NULL,
	name varchar(200) NOT NULL,
	create_date timestamp NOT NULL,
	CONSTRAINT unique_email_constraint UNIQUE (email),
	CONSTRAINT user_id_pkey PRIMARY KEY (user_id)
);

CREATE TABLE "point_sale"."orders" (
	order_id int4 NOT NULL DEFAULT nextval('point_sale.order_id_seq'::text::regclass),
	client varchar(100) NOT NULL,
	total decimal(10) NOT NULL,
	user_id int4 NOT NULL,
	create_date timestamp NOT NULL,
	update_date timestamp NULL,
	CONSTRAINT order_id_pkey PRIMARY KEY (order_id),
	CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES "point_sale"."users"(user_id)
);

CREATE INDEX user_order_index ON "point_sale"."orders" USING btree (user_id);

CREATE TABLE "point_sale"."products" (
	product_id int4 NOT NULL DEFAULT nextval('point_sale.product_id_seq'::text::regclass),
	name varchar(100) NOT NULL,
	amount int4 NOT NULL,
	unit_price decimal(10,2) NOT NULL,
	order_id int4 NOT NULL,
	create_date timestamp NOT NULL,
	CONSTRAINT product_id_pkey PRIMARY KEY (product_id),
	CONSTRAINT order_id_fkey FOREIGN KEY (order_id) REFERENCES point_sale.orders(order_id)
);

CREATE INDEX order_product_index ON "point_sale"."products" USING btree (order_id);
