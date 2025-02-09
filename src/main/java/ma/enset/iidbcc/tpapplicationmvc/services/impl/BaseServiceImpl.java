package ma.enset.iidbcc.tpapplicationmvc.services.impl;

import ma.enset.iidbcc.tpapplicationmvc.dao.Dao;
import ma.enset.iidbcc.tpapplicationmvc.services.IBaseService;

import java.util.List;

public abstract class BaseServiceImpl<E, U> implements IBaseService<E, U> {

    protected abstract E beforeSave(E entity);
    protected abstract void afterSave(E entity);
    protected abstract E beforeUpdate(E entity);
    protected abstract void afterUpdate(E entity);

    protected Dao<E, U> dao;

    public BaseServiceImpl(Dao<E, U> dao) {
        this.dao = dao;
    }

    @Override
    public void save(E entity) {
        E item = beforeSave(entity);
        try {
            dao.save(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        afterSave(entity);
    }

    @Override
    public void update(E entity) {
        E item = beforeUpdate(entity);
        try {
            dao.update(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        afterUpdate(item);
    }

    @Override
    public void delete(U id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E getById(U id) {
        return dao.findOne(id).orElse(null);
    }

    @Override
    public List<E> findAll() {
        return dao.findAll();
    }
}
