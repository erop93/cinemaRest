package repository;

import entity.Actor;
import entity.Genre;
import entity.Movie;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для сущности Movie с реализацией CRUD методов.
 */
public class MovieDAO {

    /**
     * Метод для получения всех фильмов.
     *
     * @return возвращает список всех фильмов.
     */
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int movieId = resultSet.getInt("movie_id");
                String movieName = resultSet.getString("movie_name");
                int genreId = resultSet.getInt("genre_id");
                Genre genre = new GenreDAO().getGenreById(genreId);
                Movie movie = new Movie(movieId, movieName, genre);

                List<Actor> actors = getActorsForMovie(movieId);
                movie.setActors(actors);
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
        return movies;
    }

    /**
     * Метод для получения фильма по его id.
     *
     * @param movieId - id фильма.
     * @return возвращает фильм
     */
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        String query = "SELECT * FROM movies WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String movieName = resultSet.getString("movie_name");
                int genreId = resultSet.getInt("genre_id");
                Genre genre = new GenreDAO().getGenreById(genreId);

                movie = new Movie(movieId, movieName, genre);

                List<Actor> actors = getActorsForMovie(movieId);
                movie.setActors(actors);
            }
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
        return movie;
    }

    /**
     * Метод для добавления фильма.
     * @param movie - фильм
     */
    public void addMovie(Movie movie) {
        String query = "INSERT INTO movies (movie_name, genre_id) VALUES (?, ?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getGenre().getGenreId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                movie.setMovieId(resultSet.getInt(1));
            }
            addActorsForMovie(movie.getMovieId(), movie.getActors());
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

    /**
     * Метод для обновления данных фильма - названия и жанра.
     * @param movie - фильм.
     */
    public void updateMovie(Movie movie) {
        String query = "UPDATE movies SET movie_name = ?, genre_id = ? WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getGenre().getGenreId());
            preparedStatement.setInt(3, movie.getMovieId());
            preparedStatement.executeUpdate();

            deleteActorsForMovie(movie.getMovieId());
            addActorsForMovie(movie.getMovieId(), movie.getActors());
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

    /**
     * Метод для удаления фильма.
     * Сначала удаляем связи актеров с фильмом с помощью метода deleteActorsForMovie, а затем и сам фильм.
     * @param movieId - id фильма
     */
    public void deleteMovie(int movieId) {
        deleteActorsForMovie(movieId);
        String query = "DELETE FROM movies WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, movieId);
            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

    /**
     * Дополнительный метод для получения актеров для фильма.
     * @param movieId - id фильма
     * @return возвращает список актеров
     */
    private List<Actor> getActorsForMovie(int movieId) {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT a.actor_id, a.actor_name FROM actors a " +
                "JOIN actor_movies am ON a.actor_id = am.actor_id " +
                "WHERE am.movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int actorId = resultSet.getInt("actor_id");
                String actorName = resultSet.getString("actor_name");
                actors.add(new Actor(actorId, actorName));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
        return actors;
    }

    /**
     * Дополнительный метод для добавления актеров для фильма.
     * @param movieId - id фильма
     * @param actors - список актеров
     */
    private void addActorsForMovie(int movieId, List<Actor> actors) {
        String query = "INSERT INTO actor_movies (actor_id, movie_id) VALUES (?, ?)";

        try (Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Actor actor : actors) {
                preparedStatement.setInt(1, actor.getActorId());
                preparedStatement.setInt(2, movieId);
                preparedStatement.executeUpdate();
            }
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

    /**
     * Дополнительный метод для удаления актеров для фильма.
     * @param movieId - id фильма
     */
    private void deleteActorsForMovie(int movieId) {
        String query = "DELETE FROM actor_movies WHERE movie_id = ?";

        try (Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, movieId);
            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL error!");
        }
    }

}
