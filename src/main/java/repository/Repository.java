package repository;

public interface Repository<T> {

    T get(int id);
    void create(T entity);
    void update(T entity);
    void delete(int id);


}
