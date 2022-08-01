package com.mpas.cems.xml.repos.impl;

import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.StorageRepo;

import javax.sql.DataSource;
import java.util.Optional;


public class JdbcStorageRepo extends JdbcAbstractRepo<Storage> implements StorageRepo {

    public JdbcStorageRepo() {
    }

    public JdbcStorageRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Optional<Storage> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        return Optional.empty();
    }
}
