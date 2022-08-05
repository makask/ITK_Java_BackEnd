CREATE SEQUENCE seq1 START WITH 1;

CREATE TABLE CUSTOMER (
 id BIGINT NOT NULL PRIMARY KEY,
 first_name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 code VARCHAR(100) NOT NULL,
 customer_type VARCHAR(100)
);

CREATE TABLE Phone (
 id BIGINT NOT NULL PRIMARY KEY,
 customer_id BIGINT NOT NUll,
 phone_type VARCHAR(255),
 value VARCHAR(255)
);

INSERT INTO CUSTOMER (id, first_name, last_name, code, customer_type) VALUES (NEXT VALUE FOR seq1, 'Jill', 'Smith', 123, 'customer_type.private');
INSERT INTO CUSTOMER (id, first_name, last_name, code, customer_type) VALUES (NEXT VALUE FOR seq1, 'Jack', 'Black', 321, 'customer_type.corporate');

INSERT INTO Phone (phone_type, value, customer_id, id) VALUES ('phone_type.mobile', '50000000', 1, NEXT VALUE FOR seq1);
