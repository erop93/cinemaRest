package service;

import entity.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ActorRepository;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void getAllActorsTest() throws SQLException {
        List<Actor> actors = Arrays.asList(
                new Actor(1, "Leonardo DiCaprio"),
                new Actor(2, "Bruce Willis")
        );
        when(actorRepository.getAllActors()).thenReturn(actors);
        List<Actor> result = actorService.getAllActors();
        assertEquals(2, result.size());
        verify(actorRepository, times(1)).getAllActors();
    }

    @Test
    void addActorTest() throws SQLException {
        Actor actor = new Actor(1, "Leonardo DiCaprio");
        actorService.addActor(actor);
        verify(actorRepository, times(1)).addActor(actor);
    }

    @Test
    void updateActorTest() throws SQLException {
        Actor actor = new Actor(1, "Leonardo DiCaprio");
        actorService.updateActor(actor);
        verify(actorRepository, times(1)).updateActor(actor);
    }

    @Test
    void deleteActorByIdTest() throws SQLException {
        int actorId = 1;
        actorService.deleteActorById(actorId);
        verify(actorRepository, times(1)).deleteActor(actorId);
    }
}