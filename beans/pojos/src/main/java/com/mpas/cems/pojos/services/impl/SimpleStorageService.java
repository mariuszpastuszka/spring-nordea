package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.AbstractRepo;
import com.mpas.cems.pojos.repos.StorageRepo;
import com.mpas.cems.pojos.services.StorageService;


public class SimpleStorageService extends SimpleAbstractService<Storage> implements StorageService {
    private StorageRepo repo;

    @Override
    public Storage createStorage(String name, String location) {
        var storage = new Storage();
        storage.setName(name);
        storage.setLocation(location);
        repo.save(storage);
        return storage;
    }

    public void setRepo(StorageRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<Storage> getRepo() {
        return repo;
    }
}
