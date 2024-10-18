package service;

import entity.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ActorDAO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActorServiceTest {

    @Mock
    private ActorDAO actorDAO;

    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void getAllTest() throws SQLException {
        List<Actor> actors = Arrays.asList(
                new Actor(1, "Leonardo DiCaprio"),
                new Actor(2, "Bruce Willis")
        );
        when(actorDAO.getAll()).thenReturn(actors);
        List<Actor> result = actorService.getAll();
        assertEquals(2, result.size());
        verify(actorDAO, times(1)).getAll();
    }

    @Test
    void addTest() throws SQLException {
        Actor actor = new Actor(1, "Leonardo DiCaprio");
        actorService.add(actor);
        verify(actorDAO, times(1)).add(actor);
    }

    @Test
    void updateTest() throws SQLException {
        Actor actor = new Actor(1, "Leonardo DiCaprio");
        actorService.update(actor);
        verify(actorDAO, times(1)).update(actor);
    }

    @Test
    void deleteTest() throws SQLException {
        int actorId = 1;
        actorService.delete(actorId);
        verify(actorDAO, times(1)).delete(actorId);
    }
}