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
    void getAllGenresTest() throws SQLException {
        List<Genre> genres = Arrays.asList(
                new Genre(1, "Drama"),
                new Genre(2, "Horror")
        );
        when(genreDAO.getAllGenres()).thenReturn(genres);
        List<Genre> result = genreService.getAllGenres();
        assertEquals(2, result.size());
        verify(genreDAO, times(1)).getAllGenres();
    }

    @Test
    void addGenreTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        genreService.addGenre(genre);
        verify(genreDAO, times(1)).addGenre(genre);
    }

    @Test
    void updateGenreTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        genreService.updateGenre(genre);
        verify(genreDAO, times(1)).updateGenre(genre);
    }

    @Test
    void deleteGenreTest() throws SQLException {
        int genreId = 1;
        genreService.deleteGenre(genreId);
        verify(genreDAO, times(1)).deleteGenre(genreId);
    }
}