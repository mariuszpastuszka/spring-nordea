package com.mpas.cems.aop.service;

import com.mpas.cems.dao.Storage;

import java.util.Optional;


public interface StorageService {
    Optional<Storage> findByName(String name);

    Optional<Storage> findByLocation(String location);

    void save(Storage storage);

    void saveEvidenceSet(Storage storage);

    void delete(Storage entity);

    int deleteById(Long entityId);

    Optional<Storage> findById(Long entityId);

}
