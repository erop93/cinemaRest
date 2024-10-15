package repository;

import entity.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

public class ActorDAOTest extends TestContainersTest {

    private ActorDAO actorDAO = new ActorDAO();

    @Test
    void getAllActorsTest() throws SQLException {
        actorDAO.addActor(new Actor(0,"Leonardo DiCaprio"));
        actorDAO.addActor(new Actor(0, "Bruce Willis"));
        List<Actor> actors = actorDAO.getAllActors();
        assertEquals(2, actors.size());
    }

    @Test
    void addActorTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorDAO.addActor(actor);
        assertNotNull(actorDAO.getActorById(actor.getActorId()));
    }

    @Test
    void updateActorTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorDAO.addActor(actor);
        actor.setActorName("Updated Name");
        actorDAO.updateActor(actor);

        Actor updatedActor = actorDAO.getActorById(actor.getActorId());
        assertEquals("Updated Name", updatedActor.getActorName());
    }

    @Test
    void deleteActorTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorDAO.addActor(actor);
        actorDAO.deleteActor(actor.getActorId());
        assertNull(actorDAO.getActorById(actor.getActorId()));
    }
}