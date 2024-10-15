package service;

import entity.Genre;
import repository.GenreDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для класса Genre, в котором методы работают через экземпляр слоя репозитория GenreRepository.
 */
public class GenreService {

    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public List<Genre> getAllGenres() throws SQLException {
        return genreDAO.getAllGenres();
    }

    public Genre getGenreById(int genreId) throws SQLException {
        return genreDAO.getGenreById(genreId);
    }

    public void addGenre(Genre genre) throws SQLException {
        genreDAO.addGenre(genre);
    }

    public void updateGenre(Genre genre) throws SQLException {
        genreDAO.updateGenre(genre);
    }

    public void deleteGenre(int genreId) throws SQLException {
        genreDAO.deleteGenre(genreId);
    }
}
