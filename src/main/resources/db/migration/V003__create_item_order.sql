CREATE TABLE `orders_products` (
	id				BIGINT,
	order_id		BIGINT,
	products_id		BIGINT,
	product_price	DOUBLE,
	amount			INTEGER,
	total_price		DOUBLE,
	PRIMARY KEY 	(id)
);

ALTER TABLE generator_table
ADD COLUMN IO_KEY VARCHAR(255),
ADD COLUMN IO_KEY_NEXT BIGINT;