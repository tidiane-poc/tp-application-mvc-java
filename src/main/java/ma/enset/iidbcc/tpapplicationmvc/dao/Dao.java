package ma.enset.iidbcc.tpapplicationmvc.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, U> {
    void save(E entity);
    void update(E entity);
    void delete(U id);
    Optional<E> findOne(U id);
    List<E> findAll();
}
