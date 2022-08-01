package com.mpas.cems.pojos.services;


public interface AbstractService<T> {
    void save(T entity);

    T findById(Long entityId);

    void delete(T entity);

    int deleteById(Long entityId);
}
