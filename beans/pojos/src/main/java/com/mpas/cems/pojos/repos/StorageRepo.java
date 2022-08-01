package com.mpas.cems.pojos.repos;

import com.mpas.cems.dao.Storage;

import java.util.Optional;


public interface StorageRepo extends AbstractRepo<Storage> {

    Optional<Storage> findByName(String name);

    Optional<Storage> findByLocation(String location);
}
