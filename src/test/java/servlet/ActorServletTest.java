package servlet;

import entity.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.ActorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActorServletTest {

    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorServlet actorServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void doGetActorByIdTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(printWriter);

        Actor actor = new Actor(1, "Leonardo DiCaprio");
        when(actorService.getById(1)).thenReturn(actor);

        actorServlet.doGet(request, response);
        printWriter.flush();

        assertTrue(stringWriter.toString().contains("Leonardo DiCaprio"));
    }

    @Test
    public void doPostAddActorTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("actorName")).thenReturn("Leonardo DiCaprio");
        actorServlet.doPost(request, response);

        verify(actorService, times(1)).add(any(Actor.class));
        verify(response, times(1)).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    public void doPutUpdateActorTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("actorName")).thenReturn("Updated Name");
        actorServlet.doPut(request, response);

        verify(actorService, times(1)).update(any(Actor.class));
        verify(response, times(1)).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void doDeleteActorTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("actorId")).thenReturn("aaa");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);
        actorServlet.doDelete(request, response);

        verify(actorService, times(0)).delete(anyInt());
        verify(response, times(1)).setStatus(HttpServletResponse.SC_BAD_REQUEST);

        printWriter.flush();
    }
}