package service;

import entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.GenreDAO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServiceTest {

    @Mock
    private GenreDAO genreDAO;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTest() throws SQLException {
        List<Genre> genres = Arrays.asList(
                new Genre(1, "Drama"),
                new Genre(2, "Horror")
        );
        when(genreDAO.getAll()).thenReturn(genres);
        List<Genre> result = genreService.getAll();
        assertEquals(2, result.size());
        verify(genreDAO, times(1)).getAll();
    }

    @Test
    void addTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        genreService.add(genre);
        verify(genreDAO, times(1)).add(genre);
    }

    @Test
    void updateTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        genreService.update(genre);
        verify(genreDAO, times(1)).update(genre);
    }

    @Test
    void deleteTest() throws SQLException {
        int genreId = 1;
        genreService.delete(genreId);
        verify(genreDAO, times(1)).delete(genreId);
    }
}