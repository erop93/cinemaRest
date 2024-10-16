package servlet;

import entity.Genre;
import entity.Movie;
import repository.GenreDAO;
import repository.MovieDAO;
import service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для фильмов. Реализует HTTP методы GET, POST, PUT, DELETE.
 * Использует экземпляр класса MovieService.
 */
@WebServlet("/movies")
public class MovieServlet extends HttpServlet {
    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        movieService = new MovieService(new MovieDAO());
    }

    /**
     * Метод для получения фильмов.
     * Первая часть метода после if для получения фильма по id.
     * Вторая часть метода после else для получения списка всех фильмов.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if (idParam != null) {
            int movieId = Integer.parseInt(idParam);
            Movie movie = movieService.getById(movieId);
            if (movie != null) {
                out.println("{ \"movieId\": " + movie.getMovieId() + ", \"movieName\": \"" + movie.getMovieName() + "\" }");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("{\"error\": \"Movie not found\"}");
            }
        } else {
            List<Movie> movies = movieService.getAll();
            out.println("[");
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                out.println("{ \"movieId\": " + movie.getMovieId() + ", \"movieName\": \"" + movie.getMovieName() + "\" }");
                if (i < movies.size() - 1) {
                    out.println(",");
                }
            }
            out.println("]");
        }
    }

    /**
     * Метод для добавления фильмов.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String movieName = req.getParameter("movieName");
        int genreId = Integer.parseInt(req.getParameter("genreId"));

        Genre genre = new GenreDAO().getById(genreId);
        Movie movie = new Movie(0, movieName, genre);
        movieService.add(movie);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Метод для обновления информации о фильме.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        int movieId = Integer.parseInt(req.getParameter("id"));
        String movieName = req.getParameter("movieName");
        int genreId = Integer.parseInt(req.getParameter("genreId"));

        Genre genre = new GenreDAO().getById(genreId);
        Movie movie = new Movie(movieId, movieName, genre);
        movieService.update(movie);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Метод для удаления фильма.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int movieId = Integer.parseInt(req.getParameter("id"));

        movieService.delete(movieId);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
