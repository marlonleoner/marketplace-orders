CREATE TABLE `orders` (
	id				BIGINT NOT NULL,
	user_id			BIGINT,
	product_id		BIGINT,
	amount			INTEGER,
	total_price		DOUBLE,
	created_at 		DATETIME,
	updated_at 		DATETIME,
	PRIMARY KEY 	(id)
);

ALTER TABLE generator_table
ADD COLUMN ORDER_KEY VARCHAR(255),
ADD COLUMN ORDER_KEY_NEXT BIGINT;