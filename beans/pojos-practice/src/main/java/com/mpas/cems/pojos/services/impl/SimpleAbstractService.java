package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.AbstractEntity;
import com.mpas.cems.pojos.repos.AbstractRepo;
import com.mpas.cems.pojos.services.AbstractService;


public abstract class SimpleAbstractService<T extends AbstractEntity> implements AbstractService<T> {

    public void save(T entity) {
        getRepo().save(entity);
    }

    public T findById(Long entityId) {
        return getRepo().findById(entityId);
    }

    @Override
    public void delete(T entity) {
        getRepo().delete(entity);
    }

    @Override
    public int deleteById(Long entityId) {
        return getRepo().deleteById(entityId);
    }

    abstract AbstractRepo<T> getRepo();
}
