package repository;

import java.util.List;

/**
 * Common interface for all DAOs.
 * @param <T> - any type.
 */
public interface CrudDAO<T> {
    List<T> getAll();
    T getById(int id);
    void add(T t);
    void update(T t);
    void delete(int id);
}
