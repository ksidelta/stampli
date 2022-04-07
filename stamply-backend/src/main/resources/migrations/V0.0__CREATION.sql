CREATE TABLE users
(
    DTYPE    VARCHAR(31) NOT NULL,
    id       INTEGER UNIQUE AUTO_INCREMENT,

    login    VARCHAR(64),
    password VARCHAR(64),

    issuer   VARCHAR(64),
    uid      VARCHAR(64),


    UNIQUE INDEX (login),
    UNIQUE INDEX (uid, issuer),

    PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    name   VARCHAR(16) NOT NULL,
    userId INTEGER,

    PRIMARY KEY (userId, name),
    FOREIGN KEY (userId) REFERENCES users (id)
);