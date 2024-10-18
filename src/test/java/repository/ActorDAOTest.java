package repository;

import entity.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ActorDAOTest extends TestContainersTest {

    private ActorDAO actorDAO = new ActorDAO();
    private Actor actor;

    @BeforeEach
    public void setUp() {
        actor = new Actor(0, "Leonardo DiCaprio");
    }

    @Test
    void getAllActorsTest() {
        actorDAO.add(new Actor(0,"Leonardo DiCaprio"));
        actorDAO.add(new Actor(0, "Bruce Willis"));
        List<Actor> actors = actorDAO.getAll();
        assertEquals(2, actors.size());
    }

    @Test
    void addActorTest() {
        actorDAO.add(actor);
        assertNotNull(actorDAO.getById(actor.getActorId()));
    }

    @Test
    void updateActorTest() {
        actorDAO.add(actor);
        actor.setActorName("Updated Name");
        actorDAO.update(actor);

        Actor updatedActor = actorDAO.getById(actor.getActorId());
        assertEquals("Updated Name", updatedActor.getActorName());
    }

    @Test
    void deleteActorTest() {
        actorDAO.add(actor);
        actorDAO.delete(actor.getActorId());
        assertNull(actorDAO.getById(actor.getActorId()));
    }
}