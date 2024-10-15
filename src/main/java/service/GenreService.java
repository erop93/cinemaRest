package service;

import entity.Genre;
import repository.GenreRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для класса Genre, в котором методы работают через экземпляр слоя репозитория GenreRepository.
 */
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() throws SQLException {
        return genreRepository.getAllGenres();
    }

    public Genre getGenreById(int genreId) throws SQLException {
        return genreRepository.getGenreById(genreId);
    }

    public void addGenre(Genre genre) throws SQLException {
        genreRepository.addGenre(genre);
    }

    public void updateGenre(Genre genre) throws SQLException {
        genreRepository.updateGenre(genre);
    }

    public void deleteGenre(int genreId) throws SQLException {
        genreRepository.deleteGenre(genreId);
    }
}
