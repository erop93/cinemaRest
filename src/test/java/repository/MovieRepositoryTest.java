package repository;

import entity.Genre;
import entity.Movie;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

class MovieRepositoryTest extends TestContainersTest {

    private MovieRepository movieRepository = new MovieRepository();
    private GenreRepository genreRepository = new GenreRepository();

    @Test
    void getAllMoviesTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);

        movieRepository.addMovie(new Movie(0, "Forrest Gump", genre));
        movieRepository.addMovie(new Movie(0, "Interstellar", genre));

        List<Movie> movies = movieRepository.getAllMovies();
        assertEquals(2, movies.size());
    }

    @Test
    void addMovieTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);

        Movie movie = new Movie(0, "Forrest Gump", genre);
        movieRepository.addMovie(movie);

        assertNotNull(movieRepository.getMovieById(movie.getMovieId()));
    }

    @Test
    void updateMovieTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);

        Movie movie = new Movie(0, "Forrest Gump", genre);
        movieRepository.addMovie(movie);

        movie.setMovieName("Updated Name");
        movieRepository.updateMovie(movie);

        Movie updatedMovie = movieRepository.getMovieById(movie.getMovieId());
        assertEquals("Updated Name", updatedMovie.getMovieName());
    }

    @Test
    void deleteMovieTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);

        Movie movie = new Movie(0, "Forrest Gump", genre);
        movieRepository.addMovie(movie);

        movieRepository.deleteMovie(movie.getMovieId());
        assertNull(movieRepository.getMovieById(movie.getMovieId()));
    }
}