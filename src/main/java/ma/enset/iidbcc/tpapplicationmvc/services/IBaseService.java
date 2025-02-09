package ma.enset.iidbcc.tpapplicationmvc.services;

import java.util.List;

public interface IBaseService<T, U> {
    void save(T entity);
    void update(T entity);
    void delete(U id);
    T getById(U id);
    List<T> findAll();

}
