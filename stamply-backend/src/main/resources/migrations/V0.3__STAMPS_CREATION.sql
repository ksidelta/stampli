CREATE TABLE stamp_client
(
    clientId INTEGER,

    PRIMARY KEY (clientId)
);

CREATE TABLE stamp_business
(
    clientId   INTEGER,
    businessId INTEGER,

    PRIMARY KEY (clientId, businessId)
);


CREATE TABLE stamps
(
    id         INTEGER UNIQUE AUTO_INCREMENT,

    clientId   INTEGER,
    businessId INTEGER,

    PRIMARY KEY (id)
);
