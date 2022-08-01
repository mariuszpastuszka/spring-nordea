package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Person;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import com.mpas.cems.pojos.repos.StorageRepo;
import com.mpas.cems.pojos.services.OperationsService;
import com.mpas.cems.pojos.services.ServiceException;
import com.mpas.cems.util.CaseStatus;
import com.mpas.cems.util.CaseType;
import com.mpas.cems.util.NumberGenerator;
import com.mpas.cems.util.Rank;

import java.time.LocalDateTime;
import java.util.*;


public class SimpleOperationsService implements OperationsService {
    private CriminalCaseRepo criminalCaseRepo;
    private EvidenceRepo evidenceRepo;
    private DetectiveRepo detectiveRepo;
    private StorageRepo storageRepo;

    @Override
    public Detective createDetective(String firstName, String lastName, LocalDateTime hiringDate, Rank rank) {
        var person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setHiringDate(hiringDate);
        person.setPassword(NumberGenerator.getPassword());
        Detective detective = new Detective();
        detective.setPerson(person);
        detective.setRank(rank);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        detectiveRepo.save(detective);
        return detective;
    }

    @Override
    public CriminalCase createCriminalCase(CaseType caseType, String shortDescription, String badgeNo, Map<Evidence, String> evidenceMap) {
        var detectiveOpt = detectiveRepo.findByBadgeNumber(badgeNo);
        var criminalCase = new CriminalCase();
        criminalCase.setType(caseType);
        criminalCase.setShortDescription(shortDescription);
        detectiveOpt.ifPresent(criminalCase::setLeadInvestigator);
        criminalCaseRepo.save(criminalCase);

        evidenceMap.forEach((ev, storageName) -> {
            var storageOpt = storageRepo.findByName(storageName);
            if (storageOpt.isPresent()) {
                ev.setStorage(storageOpt.get());
                criminalCase.addEvidence(ev);
                evidenceRepo.save(ev);
            } else {
                throw new ServiceException("Evidence Storage not present in the system");
            }
        });
        return criminalCase;
    }

    @Override
    public Optional<CriminalCase> assignLeadInvestigator(String caseNumber, String leadDetectiveBadgeNo) {
        var opt = criminalCaseRepo.findByNumber(caseNumber);
        var detectiveOpt = detectiveRepo.findByBadgeNumber(leadDetectiveBadgeNo);
        if (opt.isPresent()) {
            var criminalCase = opt.get();
            if (detectiveOpt.isPresent()) {
                criminalCase.setLeadInvestigator(detectiveOpt.get());
                criminalCaseRepo.save(criminalCase);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<CriminalCase> linkEvidence(String caseNumber, List<Evidence> evidenceList) {
        var opt = criminalCaseRepo.findByNumber(caseNumber);
        if (opt.isPresent()) {
            var criminalCase = opt.get();
            criminalCase.getEvidenceSet().forEach(evidence -> {
                evidence.setCriminalCase(criminalCase);
                evidenceRepo.save(evidence);
            });
            return opt;
        }
        return Optional.empty();
    }

    @Override
    public boolean solveCase(String caseNumber, String reason) {
        criminalCaseRepo.findByNumber(caseNumber).ifPresent(criminalCase -> {
            criminalCase.setStatus(CaseStatus.CLOSED);
            criminalCase.getEvidenceSet().forEach(evidence -> {
                evidence.setArchived(true);
            });
            criminalCaseRepo.save(criminalCase);
        });
        return true;
    }

    @Override
    public Set<Detective> getAssignedTeam(String caseNumber) {
        var opt = criminalCaseRepo.findByNumber(caseNumber);
        if (opt.isPresent()) {
            return opt.get().getAssigned();
        }
        return new HashSet<>();
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
