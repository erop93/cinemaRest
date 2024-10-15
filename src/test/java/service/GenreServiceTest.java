package service;

import entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.GenreRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

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
        when(genreRepository.getAllGenres()).thenReturn(genres);
        List<Genre> result = genreService.getAllGenres();
        assertEquals(2, result.size());
        verify(genreRepository, times(1)).getAllGenres();
    }

    @Test
    void addGenreTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        genreService.addGenre(genre);
        verify(genreRepository, times(1)).addGenre(genre);
    }

    @Test
    void updateGenreTest() throws SQLException {
        Genre genre = new Genre(1, "Drama");
        genreService.updateGenre(genre);
        verify(genreRepository, times(1)).updateGenre(genre);
    }

    @Test
    void deleteGenreTest() throws SQLException {
        int genreId = 1;
        genreService.deleteGenre(genreId);
        verify(genreRepository, times(1)).deleteGenre(genreId);
    }
}