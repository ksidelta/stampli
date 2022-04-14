CREATE TABLE business_offer
(
    businessId INTEGER UNIQUE AUTO_INCREMENT,
    PRIMARY KEY (businessId)
);

CREATE TABLE offer
(
    offerId    INTEGER UNIQUE AUTO_INCREMENT,
    businessId INTEGER,
    image      LONGBLOB,
    PRIMARY KEY(offerId)
);