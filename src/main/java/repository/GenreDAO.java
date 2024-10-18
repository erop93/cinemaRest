package repository;

import entity.Genre;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для сущности Genre с реализацией CRUD методов.
 */
public class GenreDAO implements CrudDAO<Genre> {

    /**
     * Метод для получения всех жанров.
     *
     * @return возвращает список всех жанров.
     */
    public List<Genre> getAll() {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genres";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int genreId = resultSet.getInt("genre_id");
                String genreName = resultSet.getString("genre_name");
                Genre genre = new Genre(genreId, genreName);
                genres.add(genre);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
        return genres;
    }

    /**
     * Метод для получения жанра по его id.
     *
     * @param genreId - id жанра
     * @return возвращает жанр.
     */
    public Genre getById(int genreId) {
        Genre genre = null;
        String query = "SELECT * FROM genres WHERE genre_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, genreId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String genreName = resultSet.getString("genre_name");
                genre = new Genre(genreId, genreName);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
        return genre;
    }

    /**
     * Метод для добавления жанра.
     *
     * @param genre - жанр.
     */
    public void add(Genre genre) {
        String query = "INSERT INTO genres (genre_name) VALUES (?) RETURNING genre_id";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, genre.getGenreName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                genre.setGenreId(generatedId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

    /**
     * Метод для обновления жанра.
     *
     * @param genre - жанр.
     */
    public void update(Genre genre) {
        String query = "UPDATE genres SET genre_name = ? WHERE genre_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, genre.getGenreName());
            preparedStatement.setInt(2, genre.getGenreId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

    /**
     * Метод для удаления жанра.
     *
     * @param genreId - id жанра.
     */
    public void delete(int genreId) {
        String query = "DELETE FROM genres WHERE genre_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, genreId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }
}
