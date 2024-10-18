package service;

import java.util.List;

/**
 * Common interface for all services
 * @param <T> - any type.
 */
public interface CrudService<T> {

    List<T> getAll();
    T getById(int id);
    void add(T t);
    void update(T t);
    void delete(int id);



}
