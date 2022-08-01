
package com.mpas.cems.dj.services.impl;

import com.mpas.cems.dao.Storage;
import com.mpas.cems.dj.repos.StorageRepo;
import com.mpas.cems.dj.services.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private StorageRepo storageRepo;

    public StorageServiceImpl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Override
    public List<Storage> findAll() {
        return storageRepo.findAll();
    }

    @Override
    public Storage findById(Long id) {
        Optional<Storage> storageOptional =  storageRepo.findById(id);
        return storageOptional.orElseGet(Storage::new);
    }

    @Override
    public Storage save(Storage storage) {
        return storageRepo.save(storage);
    }
}
