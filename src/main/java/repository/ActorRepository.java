package repository;

import entity.Actor;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository {

    public List<Actor> getAllActors() {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public void addActor(Actor actor) {
        String query = "INSERT INTO actors (actor_name) VALUES(?)";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, actor.getActorName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Actor getActorById(int actorId) {
        String query = "SELECT * FROM actors WHERE actor_id = ?";

        Actor actor = null;

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, actorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String actorName = resultSet.getString("actor_name");
                actor = new Actor(actorId, actorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    public void updateActor(Actor actor) {
        String query = "UPDATE actors SET actor_name = ? WHERE actor_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, actor.getActorName());
            preparedStatement.setInt(2, actor.getActorId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteActor(int actorId) {
        String query = "DELETE FROM actors WHERE actor_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, actorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
