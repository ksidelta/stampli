CREATE TABLE business
(
    id            INTEGER UNIQUE AUTO_INCREMENT,
    owner_id      INTEGER UNIQUE NOT NULL,
    business_name VARCHAR(64)    NOT NULL,
    logo          LONGBLOB,
    PRIMARY KEY (id)
)
