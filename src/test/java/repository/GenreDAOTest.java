package repository;

import entity.Genre;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

class GenreDAOTest extends TestContainersTest {

    private GenreDAO genreDAO = new GenreDAO();

    @Test
    void getAllTest() throws SQLException {
        genreDAO.add(new Genre(0, "Drama"));
        genreDAO.add(new Genre(1, "Horror"));
        List<Genre> genres = genreDAO.getAll();
        assertEquals(2, genres.size());
    }

    @Test
    void addTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.add(genre);
        assertNotNull(genreDAO.getById(genre.getGenreId()));
    }

    @Test
    void updateTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.add(genre);

        genre.setGenreName("New Genre");
        genreDAO.update(genre);

        Genre updatedGenre = genreDAO.getById(genre.getGenreId());
        assertEquals("New Genre", updatedGenre.getGenreName());
    }

    @Test
    void deleteTest() throws SQLException {
        Genre genre = new Genre(0, "Drama");
        genreDAO.add(genre);
        genreDAO.delete(genre.getGenreId());
        assertNull(genreDAO.getById(genre.getGenreId()));
    }
}