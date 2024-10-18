package repository;

import entity.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

public class ActorDAOTest extends TestContainersTest {

    private ActorDAO actorDAO = new ActorDAO();

    @Test
    void getAllTest() throws SQLException {
        actorDAO.add(new Actor(0,"Leonardo DiCaprio"));
        actorDAO.add(new Actor(0, "Bruce Willis"));
        List<Actor> actors = actorDAO.getAll();
        assertEquals(2, actors.size());
    }

    @Test
    void addTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorDAO.add(actor);
        assertNotNull(actorDAO.getById(actor.getActorId()));
    }

    @Test
    void updateTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorDAO.add(actor);
        actor.setActorName("Updated Name");
        actorDAO.update(actor);

        Actor updatedActor = actorDAO.getById(actor.getActorId());
        assertEquals("Updated Name", updatedActor.getActorName());
    }

    @Test
    void deleteTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorDAO.add(actor);
        actorDAO.delete(actor.getActorId());
        assertNull(actorDAO.getById(actor.getActorId()));
    }
}