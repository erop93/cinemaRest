package service;

import entity.Actor;
import repository.ActorDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для класса Actor, в котором методы работают через экземпляр слоя репозитория ActorRepository.
 */
public class ActorService {
    private final ActorDAO actorDAO;

    public ActorService(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }


    public List<Actor> getAllActors() {
        return actorDAO.getAllActors();
    }

    public Actor getActorById(int actorId) {
        return actorDAO.getActorById(actorId);
    }

    public void addActor(Actor actor) {
        actorDAO.addActor(actor);
    }

    public void updateActor(Actor actor) {
        actorDAO.updateActor(actor);
    }

    public void deleteActorById(int actorId) {
        actorDAO.deleteActor(actorId);
    }
}
