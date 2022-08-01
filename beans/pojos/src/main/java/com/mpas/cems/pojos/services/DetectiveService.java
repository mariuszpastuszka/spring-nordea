package com.mpas.cems.pojos.services;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Person;
import com.mpas.cems.util.Rank;


public interface DetectiveService extends AbstractService<Detective> {

    Detective createDetective(Person person, Rank rank);
}
