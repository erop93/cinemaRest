package servlet;

import entity.Genre;
import repository.GenreDAO;
import service.GenreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для жанров. Реализует HTTP методы GET, POST, PUT, DELETE.
 * Использует экземпляр класса GenreService.
 */
@WebServlet("/genres")
public class GenreServlet extends HttpServlet {
    private GenreService genreService;

    @Override
    public void init() throws ServletException {
        genreService = new GenreService(new GenreDAO());
    }

    /**
     * Метод для получения жанров.
     * Первая часть метода после if для получения жанра по id.
     * Вторая часть метода после else для получения списка всех жанров.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if (idParam != null) {
            int genreId = Integer.parseInt(idParam);
            try {
                Genre genre = genreService.getGenreById(genreId);
                if (genre != null) {
                    out.println("{ \"genreId\": " + genre.getGenreId() + ", \"genreName\": \"" + genre.getGenreName() + "\" }");
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"error\": \"Genre not found\"}");
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                List<Genre> genres = genreService.getAllGenres();
                out.println("[");
                for (int i = 0; i < genres.size(); i++) {
                    Genre genre = genres.get(i);
                    out.println("{ \"genreId\": " + genre.getGenreId() + ", \"genreName\": \"" + genre.getGenreName() + "\" }");
                    if (i < genres.size() - 1) {
                        out.println(",");
                    }
                }
                out.println("]");
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Метод для добавления жанров.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreName = req.getParameter("genreName");

        try {
            Genre genre = new Genre(0, genreName);
            genreService.addGenre(genre);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Метод для обновления информации о жанре.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int genreId = Integer.parseInt(req.getParameter("id"));
        String genreName = req.getParameter("genreName");

        try {
            Genre genre = new Genre(genreId, genreName);
            genreService.updateGenre(genre);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Метод для удаления жанра.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int genreId = Integer.parseInt(req.getParameter("id"));

        try {
            genreService.deleteGenre(genreId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
