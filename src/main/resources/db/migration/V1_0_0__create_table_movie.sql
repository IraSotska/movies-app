CREATE TABLE movies
(
    id              SERIAL PRIMARY KEY,
    name_russian    VARCHAR(255),
    description     VARCHAR(1000),
    name_native     VARCHAR(255),
    year_of_release date,
    rating          REAL,
    price           REAL,
    picture_path    VARCHAR(255),
    votes           REAL
);