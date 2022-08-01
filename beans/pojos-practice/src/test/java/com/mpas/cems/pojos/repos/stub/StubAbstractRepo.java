package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.AbstractEntity;
import com.mpas.cems.pojos.repos.AbstractRepo;

import java.util.HashMap;
import java.util.Map;


public abstract class StubAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {

    protected Map<Long, T> records = new HashMap<>();

    @Override
    public void save(T entity) {
        if (entity.getId() == null) {
            var id = (long) records.size() + 1;
            entity.setId(id);
        }
        records.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        records.remove(entity.getId());
    }

    @Override
    public int deleteById(Long entityId) {
        return records.remove(entityId) == null ? 0 : 1;
    }

    @Override
    public T findById(Long entityId) {
        return records.get(entityId);
    }
}
