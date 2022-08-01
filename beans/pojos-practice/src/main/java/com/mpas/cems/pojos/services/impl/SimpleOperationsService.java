package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import com.mpas.cems.pojos.repos.StorageRepo;
import com.mpas.cems.pojos.services.OperationsService;
import com.mpas.cems.util.CaseType;
import com.mpas.cems.util.Rank;
import org.apache.commons.lang3.NotImplementedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class SimpleOperationsService implements OperationsService {
    private CriminalCaseRepo criminalCaseRepo;
    private EvidenceRepo evidenceRepo;
    private DetectiveRepo detectiveRepo;
    private StorageRepo storageRepo;

    @Override
    public Detective createDetective(String firstName, String lastName, LocalDateTime hiringDate, Rank rank) {
        throw new NotImplementedException("Not needed for this section.");
    }

    @Override
    public CriminalCase createCriminalCase(CaseType caseType, String shortDescription, String badgeNo, Map<Evidence, String> evidenceMap) {
        // get detective
        // TODO 1. retrieve detective  (according to diagram 2.5)

        // create a criminal case instance
        CriminalCase criminalCase = new CriminalCase();
        // TODO 2. set fields; use ifPresent(..) to set(or not) the leadDetective field

        evidenceMap.forEach((ev, storageName) -> {
            // TODO 3. retrieve storage, throw ServiceException if not found
            // TODO 4. if storage is found, link it to the evidence and add evidence to the case
        });

        // TODO 5. save the criminal case instance
        return criminalCase;
    }

    @Override
    public Optional<CriminalCase> assignLeadInvestigator(String caseNumber, String leadDetectiveBadgeNo) {
        throw new NotImplementedException("Not needed for this section.");
    }

    @Override
    public Optional<CriminalCase> linkEvidence(String caseNumber, List<Evidence> evidenceList) {
        throw new NotImplementedException("Not needed for this section.");
    }

    @Override
    public boolean solveCase(String caseNumber, String reason) {
        throw new NotImplementedException("Not needed for this section.");
    }

    @Override
    public Set<Detective> getAssignedTeam(String caseNumber) {
        throw new NotImplementedException("Not needed for this section.");
    }

    //setters
    @Override
    public void setCriminalCaseRepo(CriminalCaseRepo criminalCaseRepo) {
        this.criminalCaseRepo = criminalCaseRepo;
    }

    @Override
    public void setEvidenceRepo(EvidenceRepo evidenceRepo) {
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    public void setDetectiveRepo(DetectiveRepo detectiveRepo) {
        this.detectiveRepo = detectiveRepo;
    }

    @Override
    public void setStorageRepo(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }
}
