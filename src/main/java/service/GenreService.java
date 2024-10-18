package service;

import entity.Genre;
import repository.GenreDAO;

import java.util.List;

/**
 * Сервисный класс для класса Genre, в котором методы работают через экземпляр слоя репозитория GenreRepository.
 */
public class GenreService implements CrudService<Genre> {

    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public List<Genre> getAll() {
        return genreDAO.getAll();
    }

    public Genre getById(int genreId) {
        return genreDAO.getById(genreId);
    }

    public void add(Genre genre) {
        genreDAO.add(genre);
    }

    public void update(Genre genre) {
        genreDAO.update(genre);
    }

    public void delete(int genreId) {
        genreDAO.delete(genreId);
    }
}
