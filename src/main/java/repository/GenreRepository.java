package repository;

import entity.Genre;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository {

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();

        String query = "SELECT * FROM genre";

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
            e.printStackTrace();
        }
        return genres;
    }

    public void addGenre(Genre genre) {
        String query = "INSERT INTO genre (genre_name) VALUES (?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, genre.getGenreName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Genre getGenreById(int genreId) {
        Genre genre = null;
        String query = "SELECT * FROM genre WHERE genre_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, genreId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String genreName = resultSet.getString("genre_name");
                genre = new Genre(genreId, genreName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public void updateGenre(Genre genre) {
        String query = "UPDATE genre SET genre_name = ? WHERE genre_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, genre.getGenreName());
            preparedStatement.setInt(2, genre.getGenreId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGenre(int genreId) {
        String query = "DELETE FROM genre WHERE genre_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, genreId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
