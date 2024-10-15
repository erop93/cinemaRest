package service;

import entity.Actor;
import repository.ActorRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный класс для класса Actor, в котором методы работают через экземпляр слоя репозитория ActorRepository.
 */
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    public List<Actor> getAllActors() throws SQLException {
        return actorRepository.getAllActors();
    }

    public Actor getActorById(int actorId) throws SQLException {
        return actorRepository.getActorById(actorId);
    }

    public void addActor(Actor actor) throws SQLException {
        actorRepository.addActor(actor);
    }

    public void updateActor(Actor actor) throws SQLException {
        actorRepository.updateActor(actor);
    }

    public void deleteActorById(int actorId) throws SQLException {
        actorRepository.deleteActor(actorId);
    }
}
