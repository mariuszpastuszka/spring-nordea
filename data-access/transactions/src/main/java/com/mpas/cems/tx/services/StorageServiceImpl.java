
package com.mpas.cems.tx.services;

import com.mpas.cems.aop.service.StorageService;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.repos.StorageRepo;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class StorageServiceImpl implements StorageService {
    private Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    private StorageRepo storageRepo;

    public StorageServiceImpl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Optional<Storage> findById(Long entityId) {
        logger.debug(">>> Preparing to execute REPO.findById");
        Optional<Storage> opt =  storageRepo.findById(entityId);
        logger.debug(">>> Done executing REPO.findById");
        return opt;
    }

    @Override
    public Optional<Storage> findByName(String name) {
        return storageRepo.findByName(name);
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        return storageRepo.findByName(location);
    }

    @Override
    public void save(Storage storage) {
        storageRepo.save(storage);
    }

    @Override
    public void saveEvidenceSet(Storage storage) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public void delete(Storage entity) {
        storageRepo.delete(entity);
    }

    @Override
    public int deleteById(Long entityId) {
        return storageRepo.deleteById(entityId);
    }
}
