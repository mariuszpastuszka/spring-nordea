package com.mpas.cems.aop.service;

import com.mpas.cems.aop.ApressService;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.repos.StorageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@ApressService
@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    private StorageRepo storageRepo;

    public StorageServiceImpl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Override
    public Optional<Storage> findByName(String name) {
        return storageRepo.findByName(name);
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        return storageRepo.findByLocation(location);
    }

    @Override
    public void save(Storage storage) {
        storageRepo.save(storage);
        saveEvidenceSet(storage);
    }

    @Override
    public void saveEvidenceSet(Storage storage) {
        //mock method to test the proxy nature
        storage.getEvidenceSet().forEach(ev -> {
            logger.info(" ---> Pretending to save evidence with number {}", ev.getNumber());
        });
    }

    @Override
    public void delete(Storage entity) {
        storageRepo.delete(entity);
    }

    @Override
    public int deleteById(Long entityId) {
        return storageRepo.deleteById(entityId);
    }

    @Override
    public Optional<Storage> findById(Long entityId) {
        return storageRepo.findById(entityId);
    }

}
