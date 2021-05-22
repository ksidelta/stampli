CREATE TABLE challenge
(
    business_id  INTEGER NOT NULL,
    issuer_id    INTEGER NOT NULL,

    in_progress  BOOL    NOT NULL,
    random_nonce INTEGER NULL,

    PRIMARY KEY (business_id, issuer_id)
)
