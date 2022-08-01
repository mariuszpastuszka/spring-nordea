package com.mpas.cems.repos.impl;

import com.mpas.cems.dao.AbstractEntity;
import com.mpas.cems.repos.AbstractRepo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;


public class JdbcAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {

    protected JdbcTemplate jdbcTemplate;

    public JdbcAbstractRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(T entity) {
    }

    @Override
    public T update(T entity) {
        return null;
    }


    @Override
    public void delete(T entity) {

    }

    @Override
    public int deleteById(Long entityId) {
        return 0;
    }

    @Override
    public Optional<T> findById(Long entityId) {
        return null;
    }

}
