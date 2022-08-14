CREATE TABLE users (
	user_id SERIAL,
	username VARCHAR(200) NOT NULL UNIQUE,
	email VARCHAR(200) NOT NULL,
	"password" VARCHAR(200) NOT NULL
	);


CREATE TABLE reimburse (
	reimburse_id SERIAL PRIMARY KEY,
	user_id INT,
	ticket INT,
	reason VARCHAR(2000) NOT NULL,
	amount DECIMAL(1000, 2) NOT NULL,
	pending BOOL
    );