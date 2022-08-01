
package com.mpas.cems.tx.services;

import com.mpas.cems.aop.service.DetectiveService;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.DetectiveRepo;
import com.mpas.cems.util.EmploymentStatus;
import com.mpas.cems.util.NumberGenerator;
import com.mpas.cems.util.Rank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;
import java.util.Set;


@Service("programaticDetectiveService")
public class ProgramaticDetectiveService implements DetectiveService {
    private DetectiveRepo detectiveRepo;
    private TransactionTemplate txTemplate;

    public ProgramaticDetectiveService(DetectiveRepo detectiveRepo, PlatformTransactionManager transactionManager) {
        this.detectiveRepo = detectiveRepo;
        this.txTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public Optional<Detective> findById(Long id) {
        return txTemplate.execute(status -> {
            Optional<Detective> opt = null;
            try {
                opt = detectiveRepo.findById(id);
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return opt;
        });
    }

    @Override
    public Set<Detective> findAll() {
        return txTemplate.execute(status ->  detectiveRepo.findAll());
    }

    @Override
    public Detective createDetective(Person person, Rank rank) {
        Detective detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        detective.setArmed(false);
        detective.setStatus(EmploymentStatus.ACTIVE);

        return txTemplate.execute(status -> {
            try {
                detectiveRepo.save(detective);
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return detective;
        });
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return txTemplate.execute(status -> detectiveRepo.findByBadgeNumber(badgeNumber));
    }

    @Override
    public Set<Detective> findByRank(Rank rank) {
        return txTemplate.execute(status -> detectiveRepo.findbyRank(rank));
    }
}
