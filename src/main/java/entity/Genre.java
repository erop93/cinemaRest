package entity;

import java.util.List;

/**
 * Сущность - жанр фильма.
 * Имеет 3 поля - id жанра, название жанра, список фильмов с одним жанром.
 * А так же содержит 2 конструктора (пустой и с полями id, имя), геттеры и сеттеры для всех полей.
 * Отношение жанра к фильмам - one to many (у одного жанра много фильмов)
 */
public class Genre {
    private int genreId;
    private String genreName;
    private List<Movie> movies;

    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public Genre() {}

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
