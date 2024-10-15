package repository;

import entity.Genre;
import entity.Movie;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

class MovieDAOTest extends TestContainersTest {

    private MovieDAO movieDAO = new MovieDAO();
    private GenreDAO genreDAO = new GenreDAO();

    @Test
    void getAllMoviesTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);

        movieDAO.addMovie(new Movie(0, "Forrest Gump", genre));
        movieDAO.addMovie(new Movie(0, "Interstellar", genre));

        List<Movie> movies = movieDAO.getAllMovies();
        assertEquals(2, movies.size());
    }

    @Test
    void addMovieTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);

        Movie movie = new Movie(0, "Forrest Gump", genre);
        movieDAO.addMovie(movie);

        assertNotNull(movieDAO.getMovieById(movie.getMovieId()));
    }

    @Test
    void updateMovieTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);

        Movie movie = new Movie(0, "Forrest Gump", genre);
        movieDAO.addMovie(movie);

        movie.setMovieName("Updated Name");
        movieDAO.updateMovie(movie);

        Movie updatedMovie = movieDAO.getMovieById(movie.getMovieId());
        assertEquals("Updated Name", updatedMovie.getMovieName());
    }

    @Test
    void deleteMovieTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);

        Movie movie = new Movie(0, "Forrest Gump", genre);
        movieDAO.addMovie(movie);

        movieDAO.deleteMovie(movie.getMovieId());
        assertNull(movieDAO.getMovieById(movie.getMovieId()));
    }
}