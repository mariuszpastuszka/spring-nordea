package com.mpas.cems.pojos.repos;

import com.mpas.cems.dao.Detective;

import java.util.Optional;


public interface DetectiveRepo extends AbstractRepo<Detective> {

    Optional<Detective> findByBadgeNumber(String badgeNumber);
}
