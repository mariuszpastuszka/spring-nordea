package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Person;
import com.mpas.cems.pojos.repos.AbstractRepo;
import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.services.DetectiveService;
import com.mpas.cems.util.NumberGenerator;
import com.mpas.cems.util.Rank;


public class SimpleDetectiveService extends SimpleAbstractService<Detective> implements DetectiveService {

    private DetectiveRepo repo;

    @Override
    public Detective createDetective(Person person, Rank rank) {
        Detective detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        repo.save(detective);
        return detective;
    }

    public void setRepo(DetectiveRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<Detective> getRepo() {
        return repo;
    }
}
