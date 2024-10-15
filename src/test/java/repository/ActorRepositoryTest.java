package repository;

import entity.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

public class ActorRepositoryTest extends TestContainersTest {

    private ActorRepository actorRepository = new ActorRepository();

    @Test
    void getAllActorsTest() throws SQLException {
        actorRepository.addActor(new Actor(0,"Leonardo DiCaprio"));
        actorRepository.addActor(new Actor(0, "Bruce Willis"));
        List<Actor> actors = actorRepository.getAllActors();
        assertEquals(2, actors.size());
    }

    @Test
    void addActorTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorRepository.addActor(actor);
        assertNotNull(actorRepository.getActorById(actor.getActorId()));
    }

    @Test
    void updateActorTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorRepository.addActor(actor);
        actor.setActorName("Updated Name");
        actorRepository.updateActor(actor);

        Actor updatedActor = actorRepository.getActorById(actor.getActorId());
        assertEquals("Updated Name", updatedActor.getActorName());
    }

    @Test
    void deleteActorTest() throws SQLException {
        Actor actor = new Actor(0, "Leonardo DiCaprio");
        actorRepository.addActor(actor);
        actorRepository.deleteActor(actor.getActorId());
        assertNull(actorRepository.getActorById(actor.getActorId()));
    }
}