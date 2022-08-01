
package com.mpas.cems.dj.services.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dj.repos.CriminalCaseRepo;
import com.mpas.cems.dj.services.CriminalCaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CriminalCaseServiceImpl implements CriminalCaseService {

    private CriminalCaseRepo criminalCaseRepo;

    public CriminalCaseServiceImpl(CriminalCaseRepo criminalCaseRepo) {
        this.criminalCaseRepo = criminalCaseRepo;
    }

    @Override
    public List<CriminalCase> findAll() {
        return criminalCaseRepo.findAll();
    }

    @Override
    public List<CriminalCase> findAllByLeadInvestigator(Detective detective) {
        return criminalCaseRepo.findByLeadInvestigator(detective);
    }

    @Override
    public CriminalCase save(CriminalCase criminalCase) {
        return criminalCaseRepo.save(criminalCase);
    }
}
