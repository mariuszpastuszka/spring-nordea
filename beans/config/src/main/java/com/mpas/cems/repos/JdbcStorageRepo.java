package com.mpas.cems.repos;

import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.StorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;


@Repository
public class JdbcStorageRepo extends JdbcAbstractRepo<Storage> implements StorageRepo {

    public JdbcStorageRepo() {
    }

    @Autowired
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
