package service;

import entity.Movie;
import repository.MovieDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для класса Movie, в котором методы работают через экземпляр слоя репозитория MovieRepository.
 */
public class MovieService {

    private final MovieDAO movieDAO;

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public Movie getMovieById(int movieId) {
        return movieDAO.getMovieById(movieId);
    }

    public void addMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    public void updateMovie(Movie movie)  {
        movieDAO.updateMovie(movie);
    }

    public void deleteMovie(int movieId) {
        movieDAO.deleteMovie(movieId);
    }
}
