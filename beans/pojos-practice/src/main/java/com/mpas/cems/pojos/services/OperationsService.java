package com.mpas.cems.pojos.services;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import com.mpas.cems.pojos.repos.StorageRepo;
import com.mpas.cems.util.CaseType;
import com.mpas.cems.util.Rank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public interface OperationsService {

    Detective createDetective(String firstName, String lastName, LocalDateTime hiringDate, Rank rank);

    CriminalCase createCriminalCase(CaseType caseType, String shortDescription, String badgeNo, Map<Evidence, String> evidenceAndLocations);

    Optional<CriminalCase> assignLeadInvestigator(String caseNumber, String leadDetectiveBadgeNo);

    Optional<CriminalCase> linkEvidence(String caseNumber, List<Evidence> evidenceList);

    boolean solveCase(String caseNumber, String reason);

    Set<Detective> getAssignedTeam(String caseNumber);

    // setter skeletons for setting repositories

    void setCriminalCaseRepo(CriminalCaseRepo criminalCaseRepo);

    void setEvidenceRepo(EvidenceRepo evidenceRepo);

    void setDetectiveRepo(DetectiveRepo detectiveRepo);

    void setStorageRepo(StorageRepo storageRepo);

}
