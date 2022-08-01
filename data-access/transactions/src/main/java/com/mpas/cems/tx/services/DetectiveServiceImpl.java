
package com.mpas.cems.tx.services;

import com.mpas.cems.aop.service.DetectiveService;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.DetectiveRepo;
import com.mpas.cems.util.EmploymentStatus;
import com.mpas.cems.util.NumberGenerator;
import com.mpas.cems.util.Rank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Transactional
@Service
public class DetectiveServiceImpl implements DetectiveService {

    private DetectiveRepo detectiveRepo;

    public DetectiveServiceImpl(DetectiveRepo detectiveRepo) {
        this.detectiveRepo = detectiveRepo;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Optional<Detective> findById(Long id) {
        return detectiveRepo.findById(id);
    }

    @Override
    public Set<Detective> findAll() {
        return detectiveRepo.findAll();
    }

    @Override
    public Detective createDetective(Person person, Rank rank) {
        Detective detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        detective.setArmed(false);
        detective.setStatus(EmploymentStatus.ACTIVE);
        detectiveRepo.save(detective);
        return detective;
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return detectiveRepo.findByBadgeNumber(badgeNumber);
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        return detectiveRepo.findbyRank(rank);
    }
}
