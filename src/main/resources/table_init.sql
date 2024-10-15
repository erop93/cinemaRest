CREATE TABLE actors
(
    actor_id   SERIAL PRIMARY KEY,
    actor_name VARCHAR(50) NOT NULL
);

CREATE TABLE genres
(
    genre_id   SERIAL PRIMARY KEY,
    genre_name VARCHAR(50) NOT NULL
);

CREATE TABLE movies
(
    movie_id   SERIAL PRIMARY KEY,
    movie_name VARCHAR(100) NOT NULL,
    genre_id   INT,
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);

CREATE TABLE actor_movies
(
    actor_id INT,
    movie_id INT,
    FOREIGN KEY (actor_id) REFERENCES actors (actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id)
);