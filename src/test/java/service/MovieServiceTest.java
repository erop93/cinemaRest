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
    void getAllTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        List<Movie> movies = Arrays.asList(
             new Movie(1, "Forrest Gump", genre),
             new Movie(2, "Interstellar", genre)
        );

        when(movieDAO.getAll()).thenReturn(movies);

        List<Movie> result = movieService.getAll();
        assertEquals(2, result.size());
        verify(movieDAO).getAll();
    }

    @Test
    void addTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        Movie movie = new Movie(1, "Forrest Gump", genre);

        movieService.add(movie);
        verify(movieDAO).add(movie);
    }

    @Test
    void updateTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        Movie movie = new Movie(1, "Forrest Gump", genre);

        movieService.update(movie);
        verify(movieDAO).update(movie);
    }

    @Test
    void deleteTest() throws SQLException {
        int movieId = 1;

        movieService.delete(movieId);
        verify(movieDAO).delete(movieId);
    }
}