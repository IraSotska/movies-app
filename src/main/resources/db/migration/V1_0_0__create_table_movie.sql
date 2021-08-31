CREATE TABLE movies
(
    id              SERIAL PRIMARY KEY,
    name_native     VARCHAR(255),
    year_of_release REAL,
    rating          REAL,
    price           REAL,
    picture_path    VARCHAR(255)
);

