package servlet;

import entity.Genre;
import entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServletTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieServlet movieServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doGetMovieByIdTest() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(printWriter);

        Genre genre = new Genre(1, "Drama");
        Movie movie = new Movie(1, "Forrest Gump", genre);
        when(movieService.getById(1)).thenReturn(movie);

        movieServlet.doGet(request, response);
        printWriter.flush();

        assertTrue(stringWriter.toString().contains("Forrest Gump"));
    }

    @Test
    void doPostAddMovieTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("movieName")).thenReturn("Forrest Gump");
        when(request.getParameter("genreId")).thenReturn("1");

        movieServlet.doPost(request, response);

        verify(movieService).add(any(Movie.class));
        verify(response).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    void doPutUpdateMovieTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("movieName")).thenReturn("Updated name");
        when(request.getParameter("genreId")).thenReturn("1");

        movieServlet.doPut(request, response);

        verify(movieService).update(any(Movie.class));
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doDeleteMovieTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        movieServlet.doDelete(request, response);

        verify(movieService).delete(1);
        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}