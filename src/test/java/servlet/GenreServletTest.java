package servlet;

import entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.GenreService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServletTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreServlet genreServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doGetGenreByIdTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(request.getParameter("id")).thenReturn("1");
        when(response.getWriter()).thenReturn(printWriter);

        Genre genre = new Genre(1, "Drama");
        when(genreService.getById(1)).thenReturn(genre);

        genreServlet.doGet(request, response);
        printWriter.flush();

        assertTrue(stringWriter.toString().contains("Drama"));
    }

    @Test
    void doPostAddGenreTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("genreName")).thenReturn("Drama");
        genreServlet.doPost(request, response);

        verify(genreService, times(1)).add(any(Genre.class));
        verify(response, times(1)).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    void doPutUpdateGenreTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("genreName")).thenReturn("UpdatedTest");
        genreServlet.doPut(request, response);

        verify(genreService, times(1)).update(any(Genre.class));
        verify(response, times(1)).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doDeleteGenreTest() throws IOException, SQLException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        genreServlet.doDelete(request, response);

        verify(genreService, times(1)).delete(1);
        verify(response, times(1)).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}