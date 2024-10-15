package service;

import entity.Genre;
import entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.MovieDAO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieDAO movieDAO;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMoviesTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        List<Movie> movies = Arrays.asList(
             new Movie(1, "Forrest Gump", genre),
             new Movie(2, "Interstellar", genre)
        );

        when(movieDAO.getAllMovies()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();
        assertEquals(2, result.size());
        verify(movieDAO, times(1)).getAllMovies();
    }

    @Test
    void addMovieTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        Movie movie = new Movie(1, "Forrest Gump", genre);

        movieService.addMovie(movie);
        verify(movieDAO, times(1)).addMovie(movie);
    }

    @Test
    void updateMovieTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        Movie movie = new Movie(1, "Forrest Gump", genre);

        movieService.updateMovie(movie);
        verify(movieDAO, times(1)).updateMovie(movie);
    }

    @Test
    void deleteMovieTest() throws SQLException {
        int movieId = 1;

        movieService.deleteMovie(movieId);
        verify(movieDAO, times(1)).deleteMovie(movieId);
    }
}