package repository;

import entity.Movie;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movie";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int movieId = resultSet.getInt("movie_id");
                String movieName = resultSet.getString("movie_name");
                int genreId = resultSet.getInt("genre_id");
                Movie movie = new Movie(movieId, movieName, genreId);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void addMovie(Movie movie) {
        String query = "INSERT INTO movie (movie_name, genre_id) VALUES (?, ?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getGenreId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Movie getMovieById(int movieId) {
        Movie movie = null;
        String query = "SELECT * FROM movie WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String movieName = resultSet.getString("movie_name");
                int genreId = resultSet.getInt("genre_id");
                movie = new Movie(movieId, movieName, genreId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public void updateMovie(Movie movie) {
        String query = "UPDATE movie SET movie_name = ?, genre_id = ? WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getGenreId());
            preparedStatement.setInt(3, movie.getMovieId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(int movieId) {
        String query = "DELETE FROM movie WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, movieId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
