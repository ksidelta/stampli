CREATE TABLE user
(
    id    INTEGER UNIQUE,
    email VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id      INTEGER UNIQUE,
    name    VARCHAR(16) NOT NULL,
    user_id INTEGER UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE authentication_password
(
    user_id INTEGER UNIQUE,
    password VARCHAR(16) NOT NULL,
    PRIMARY_KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user (id)
)
