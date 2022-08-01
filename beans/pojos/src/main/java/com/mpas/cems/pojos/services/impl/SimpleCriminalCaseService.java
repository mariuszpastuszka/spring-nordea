package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.pojos.repos.AbstractRepo;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import com.mpas.cems.pojos.services.CriminalCaseService;
import com.mpas.cems.util.CaseStatus;
import com.mpas.cems.util.CaseType;

import java.util.Set;


public class SimpleCriminalCaseService extends SimpleAbstractService<CriminalCase> implements CriminalCaseService {

    private CriminalCaseRepo repo;

    @Override
    public CriminalCase createCriminalCase(CaseType type, String shortDescription, String detailedDescription, CaseStatus caseStatus, String notes, Set<Evidence> evidenceSet, Detective leadInvestigator) {
        var criminalCase = new CriminalCase();
        criminalCase.setType(type);
        criminalCase.setShortDescription(shortDescription);
        criminalCase.setDetailedDescription(detailedDescription);
        criminalCase.setStatus(caseStatus);
        criminalCase.setNotes(notes);
        criminalCase.setEvidenceSet(evidenceSet);
        criminalCase.setLeadInvestigator(leadInvestigator);
        repo.save(criminalCase);
        return criminalCase;
    }

    public void setRepo(CriminalCaseRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<CriminalCase> getRepo() {
        return repo;
    }
}
