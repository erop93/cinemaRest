package repository;

import entity.Genre;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

class GenreDAOTest extends TestContainersTest {

    private GenreDAO genreDAO = new GenreDAO();

    @Test
    void getAllGenresTest() throws SQLException {
        genreDAO.addGenre(new Genre(0, "Drama"));
        genreDAO.addGenre(new Genre(1, "Horror"));
        List<Genre> genres = genreDAO.getAllGenres();
        assertEquals(2, genres.size());
    }

    @Test
    void addGenreTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);
        assertNotNull(genreDAO.getGenreById(genre.getGenreId()));
    }

    @Test
    void updateGenreTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);

        genre.setGenreName("New Genre");
        genreDAO.updateGenre(genre);

        Genre updatedGenre = genreDAO.getGenreById(genre.getGenreId());
        assertEquals("New Genre", updatedGenre.getGenreName());
    }

    @Test
    void deleteGenreTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.addGenre(genre);
        genreDAO.deleteGenre(genre.getGenreId());
        assertNull(genreDAO.getGenreById(genre.getGenreId()));
    }
}