package com.mpas.cems.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.util.CaseStatus;
import com.mpas.cems.util.CaseType;

import java.util.Optional;
import java.util.Set;


public interface CriminalCaseRepo extends AbstractRepo<CriminalCase> {

    Set<CriminalCase> findByLeadInvestigator(Detective detective);

    Optional<CriminalCase> findByNumber(String caseNumber);

    Set<CriminalCase> findByStatus(CaseStatus status);

    Set<CriminalCase> findByType(CaseType type);

}
