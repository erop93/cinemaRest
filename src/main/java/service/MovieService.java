package service;

import entity.Movie;
import repository.MovieRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для класса Movie, в котором методы работают через экземпляр слоя репозитория MovieRepository.
 */
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() throws SQLException {
        return movieRepository.getAllMovies();
    }

    public Movie getMovieById(int movieId) throws SQLException {
        return movieRepository.getMovieById(movieId);
    }

    public void addMovie(Movie movie) throws SQLException {
        movieRepository.addMovie(movie);
    }

    public void updateMovie(Movie movie) throws SQLException {
        movieRepository.updateMovie(movie);
    }

    public void deleteMovie(int movieId) throws SQLException {
        movieRepository.deleteMovie(movieId);
    }
}
