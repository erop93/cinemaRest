package repository;

import entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

class GenreDAOTest extends TestContainersTest {

    private GenreDAO genreDAO = new GenreDAO();
    private Genre genre;

    @BeforeEach
    public void setUp() {
        genre = new Genre(0, "Drama");
    }

    @Test
    void getAllGenresTest() {
        genreDAO.add(new Genre(0, "Drama"));
        genreDAO.add(new Genre(1, "Horror"));
        List<Genre> genres = genreDAO.getAll();
        assertEquals(2, genres.size());
    }

    @Test
    void addGenreTest() {
        genreDAO.add(genre);
        assertNotNull(genreDAO.getById(genre.getGenreId()));
    }

    @Test
    void updateGenreTest() {
        genreDAO.add(genre);

        genre.setGenreName("New Genre");
        genreDAO.update(genre);

        Genre updatedGenre = genreDAO.getById(genre.getGenreId());
        assertEquals("New Genre", updatedGenre.getGenreName());
    }

    @Test
    void deleteGenreTest() {
        genreDAO.add(genre);
        genreDAO.delete(genre.getGenreId());
        assertNull(genreDAO.getById(genre.getGenreId()));
    }
}