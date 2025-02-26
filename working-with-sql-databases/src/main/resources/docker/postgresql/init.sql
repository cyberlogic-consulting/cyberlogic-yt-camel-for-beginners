CREATE TABLE pets
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(15)                                   NOT NULL CHECK (LENGTH(name) BETWEEN 1 AND 15),
    status     VARCHAR(255)                                  NOT NULL,
    type       TEXT CHECK (type IN ('Dog', 'Cat', 'Lizard')) NOT NULL,
    birth_date DATE                                          NOT NULL
);