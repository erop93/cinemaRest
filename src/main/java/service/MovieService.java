package service;

import entity.Movie;
import repository.MovieDAO;

import java.util.List;

/**
 * Сервисный класс для класса Movie, в котором методы работают через экземпляр слоя репозитория MovieRepository.
 */
public class MovieService {

    private final MovieDAO movieDAO;

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getAll() {
        return movieDAO.getAll();
    }

    public Movie getById(int movieId) {
        return movieDAO.getById(movieId);
    }

    public void add(Movie movie) {
        movieDAO.add(movie);
    }

    public void update(Movie movie)  {
        movieDAO.update(movie);
    }

    public void delete(int movieId) {
        movieDAO.delete(movieId);
    }
}
