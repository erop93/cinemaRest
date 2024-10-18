package service;

import entity.Actor;
import repository.ActorDAO;

import java.util.List;

/**
 * Сервисный класс для класса Actor, в котором методы работают через экземпляр слоя репозитория ActorRepository.
 */
public class ActorService implements CrudService<Actor> {
    private final ActorDAO actorDAO;

    public ActorService(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }


    public List<Actor> getAll() {
        return actorDAO.getAll();
    }

    public Actor getById(int actorId) {
        return actorDAO.getById(actorId);
    }

    public void add(Actor actor) {
        actorDAO.add(actor);
    }

    public void update(Actor actor) {
        actorDAO.update(actor);
    }

    public void delete(int actorId) {
        actorDAO.delete(actorId);
    }
}
