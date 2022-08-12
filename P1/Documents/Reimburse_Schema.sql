CREATE TABLE users (
	user_id SERIAL,
	username VARCHAR(200) NOT NULL UNIQUE,
	email VARCHAR(200) NOT NULL,
	"password" VARCHAR(200) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (user_id)
	);


CREATE TABLE reimburse (
	reimburse_id SERIAL PRIMARY KEY,
	user_id INT,
	ticket INT,
	reason VARCHAR(2000) NOT NULL,
	amount DECIMAL(1000, 2) NOT NULL,
	pending BOOL,
	CONSTRAINT reimburse_users_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
    );