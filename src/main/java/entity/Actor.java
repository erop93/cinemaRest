package entity;

import java.util.List;

/**
 * Сущность - актер.
 * Имеет 3 поля - id актера, имя актера, список фильмов, в которых снимался актер.
 * А так же содержит 2 конструктора (пустой и с полями id, имя), геттеры и сеттеры для всех полей.
 * Отношение актеров и фильмов - many to many.
 */
public class Actor {
    private int actorId;
    private String actorName;
    private List<Movie> movies;

    public Actor(int actorId, String actorName) {
        this.actorId = actorId;
        this.actorName = actorName;
    }

    public Actor() {}

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
