package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.StorageRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;


public class StubStorageRepo extends StubAbstractRepo<Storage> implements StorageRepo {
    @Override
    public Optional<Storage> findByName(String name) {
        switch (name) {
            case "Here":
                return Optional.of(records.get(2L));
            case "There":
                return Optional.of(records.get(3L));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
