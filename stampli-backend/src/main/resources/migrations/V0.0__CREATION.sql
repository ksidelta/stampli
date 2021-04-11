CREATE TABLE users
(
    id    INTEGER UNIQUE AUTO_INCREMENT,
    email VARCHAR(64) NOT NULL,
    UNIQUE INDEX (email),
    PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    name   VARCHAR(16) NOT NULL,
    userId INTEGER,

    PRIMARY KEY (userId, name),
    FOREIGN KEY (userId) REFERENCES users (id)
);

CREATE TABLE user_authentication_password
(
    userId   INTEGER UNIQUE,
    password VARCHAR(16) NOT NULL,

    PRIMARY KEY (userId),
    FOREIGN KEY (userId) REFERENCES users (id)
)
