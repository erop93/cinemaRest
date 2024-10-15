package repository;

import entity.Genre;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

class GenreRepositoryTest extends TestContainersTest {

    private GenreRepository genreRepository = new GenreRepository();

    @Test
    void getAllGenresTest() throws SQLException {
        genreRepository.addGenre(new Genre(0, "Drama"));
        genreRepository.addGenre(new Genre(1, "Horror"));
        List<Genre> genres = genreRepository.getAllGenres();
        assertEquals(2, genres.size());
    }

    @Test
    void addGenreTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);
        assertNotNull(genreRepository.getGenreById(genre.getGenreId()));
    }

    @Test
    void updateGenreTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);

        genre.setGenreName("New Genre");
        genreRepository.updateGenre(genre);

        Genre updatedGenre = genreRepository.getGenreById(genre.getGenreId());
        assertEquals("New Genre", updatedGenre.getGenreName());
    }

    @Test
    void deleteGenreTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreRepository.addGenre(genre);
        genreRepository.deleteGenre(genre.getGenreId());
        assertNull(genreRepository.getGenreById(genre.getGenreId()));
    }
}