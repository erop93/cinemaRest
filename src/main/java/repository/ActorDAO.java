package repository;

import entity.Actor;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для сущности Actor с реализацией CRUD методов.
 */
public class ActorDAO {

    /**
     * Метод для получения всех актеров.
     *
     * @return возвращает список всех актеров.
     */
    public List<Actor> getAllActors() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT * FROM actors";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int actorId = resultSet.getInt("actor_id");
                String actorName = resultSet.getString("actor_name");
                Actor actor = new Actor(actorId, actorName);
                actors.add(actor);
            }
        }
        return actors;
    }

    /**
     * Метод для получения актера по его id.
     *
     * @param actorId - id актера.
     * @return возвращает актера
     */
    public Actor getActorById(int actorId) throws SQLException {
        Actor actor = null;
        String query = "SELECT * FROM actors WHERE actor_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, actorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String actorName = resultSet.getString("actor_name");
                actor = new Actor(actorId, actorName);
            }
        }
        return actor;
    }

    /**
     * Метод для добавления актера.
     *
     * @param actor - актер
     */
    public void addActor(Actor actor) throws SQLException {
        String query = "INSERT INTO actors (actor_name) VALUES(?) RETURNING actor_id";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, actor.getActorName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                actor.setActorId(generatedId);
            }
        }
    }

    /**
     * Метод для обновления имени актера.
     *
     * @param actor - актер
     */
    public void updateActor(Actor actor) throws SQLException {
        String query = "UPDATE actors SET actor_name = ? WHERE actor_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, actor.getActorName());
            preparedStatement.setInt(2, actor.getActorId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Метод для удаления актера.
     *
     * @param actorId - id актера.
     */
    public void deleteActor(int actorId) throws SQLException {
        String query = "DELETE FROM actors WHERE actor_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, actorId);
            preparedStatement.executeUpdate();
        }
    }
}
