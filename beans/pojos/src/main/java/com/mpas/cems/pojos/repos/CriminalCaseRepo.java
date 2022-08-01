package com.mpas.cems.pojos.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;

import java.util.Optional;
import java.util.Set;


public interface CriminalCaseRepo extends AbstractRepo<CriminalCase> {

    Set<CriminalCase> findByLeadInvestigator(Detective detective);

    Optional<CriminalCase> findByNumber(String caseNumber);

}
