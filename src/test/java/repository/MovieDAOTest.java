package repository;

import entity.Genre;
import entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

class MovieDAOTest extends TestContainersTest {

    private MovieDAO movieDAO = new MovieDAO();
    private GenreDAO genreDAO = new GenreDAO();
    private Genre genre;
    private Movie movie;

    @BeforeEach
    public void setUp() {
        genre = new Genre(0, "Drama");
        movie = new Movie(0, "Forrest Gump", genre);
    }

    @Test
    void getAllMoviesTest() {;
        genreDAO.add(genre);

        movieDAO.add(new Movie(0, "Forrest Gump", genre));
        movieDAO.add(new Movie(0, "Interstellar", genre));

        List<Movie> movies = movieDAO.getAll();
        assertEquals(2, movies.size());
    }

    @Test
    void addMovieTest() {
        genreDAO.add(genre);
        movieDAO.add(movie);

        assertNotNull(movieDAO.getById(movie.getMovieId()));
    }

    @Test
    void updateMovieTest() {
        genreDAO.add(genre);
        movieDAO.add(movie);

        movie.setMovieName("Updated Name");
        movieDAO.update(movie);

        Movie updatedMovie = movieDAO.getById(movie.getMovieId());
        assertEquals("Updated Name", updatedMovie.getMovieName());
    }

    @Test
    void deleteMovieTest() {
        genreDAO.add(genre);
        movieDAO.add(movie);

        movieDAO.delete(movie.getMovieId());
        assertNull(movieDAO.getById(movie.getMovieId()));
    }
}