package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.pojos.repos.DetectiveRepo;

import java.util.Optional;


public class StubDetectiveRepo extends StubAbstractRepo<Detective> implements DetectiveRepo {

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return Optional.of(records.get(1L));
    }
}
