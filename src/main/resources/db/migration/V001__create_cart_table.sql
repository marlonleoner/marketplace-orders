CREATE TABLE cart (
	id				BIGINT NOT NULL,
	user_id			BIGINT,
	product_id		BIGINT,
	amount			INTEGER,
	created_at 		DATETIME,
	updated_at 		DATETIME,
	PRIMARY KEY 	(id)
);

CREATE TABLE generator_table (
	CART_KEY		VARCHAR(255),
	CART_KEY_NEXT	BIGINT
);