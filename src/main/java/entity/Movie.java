package entity;

import java.util.List;

/**
 * Сущность - фильм.
 * Имеет 4 поля - id фильма, название фильма, жанр фильма, список актеров фильма.
 * А так же содержит 2 конструктора (пустой и с полями movieId, имя фильма, жанр фильма),
 * геттеры и сеттеры для всех полей.
 * Отношение фильмов и актеров - many to many
 */
public class Movie {
    private int movieId;
    private String movieName;
    private Genre genre;
    private List<Actor> actors;

    public Movie(int movieId, String movieName, Genre genre) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
    }

    public Movie() {}

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
