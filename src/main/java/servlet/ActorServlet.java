package servlet;

import entity.Actor;
import repository.ActorDAO;
import service.ActorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для актеров. Реализует HTTP методы GET, POST, PUT, DELETE.
 * Использует экземпляр класса ActorService.
 */
@WebServlet("/actors")
public class ActorServlet extends HttpServlet {
    private ActorService actorService;

    @Override
    public void init() {
        actorService = new ActorService(new ActorDAO());
    }

    /**
     * Метод для получения актеров.
     * Первая часть метода после if для получения актера по id.
     * Вторая часть метода после else для получения списка всех актеров.
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
            int actorId = Integer.parseInt(idParam);
            try {
                Actor actor = actorService.getById(actorId);
                if (actor != null) {
                    out.println("{ \"actorId\": " + actor.getActorId() + ", \"actorName\": \"" + actor.getActorName() + "\" }");
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"error\": \"Actor not found\"}");
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                List<Actor> actors = actorService.getAll();
                out.println("[");
                for (int i = 0; i < actors.size(); i++) {
                    Actor actor = actors.get(i);
                    out.println("{ \"actorId\": " + actor.getActorId() + ", \"actorName\": \"" + actor.getActorName() + "\" }");
                    if (i < actors.size() - 1) {
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
     * Метод для добавления актеров.
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
        String actorName = req.getParameter("actorName");

        try {
            Actor actor = new Actor(0, actorName);
            actorService.add(actor);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Метод для обновления информации об актере.
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
        int actorId = Integer.parseInt(req.getParameter("id"));
        String actorName = req.getParameter("actorName");

        try {
            Actor actor = new Actor(actorId, actorName);
            actorService.update(actor);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Метод для удаления актера.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String actorIdParam = req.getParameter("actorId");

        if (actorIdParam == null || actorIdParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid or missing 'actorId' parameter\"}");
            return;
        }
        try {
            int actorId = Integer.parseInt(actorIdParam);
            actorService.delete(actorId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid 'actorId' parameter\"}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
