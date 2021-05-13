CREATE TABLE business
(
    id            INTEGER UNIQUE AUTO_INCREMENT,
    business_name VARCHAR(64) NOT NULL,
    logo          BLOB,
    PRIMARY KEY (id)
)
