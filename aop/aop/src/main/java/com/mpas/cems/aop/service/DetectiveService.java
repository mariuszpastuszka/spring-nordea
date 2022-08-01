package com.mpas.cems.aop.service;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Person;
import com.mpas.cems.util.Rank;

import java.util.Optional;
import java.util.Set;


public interface DetectiveService {

    Set<Detective> findAll();

    Optional<Detective> findById(Long id);

    Detective createDetective(Person person, Rank rank);

    Optional<Detective> findByBadgeNumber(String badgeNumber);

    Set<Detective> findByRank(Rank rank);
}
