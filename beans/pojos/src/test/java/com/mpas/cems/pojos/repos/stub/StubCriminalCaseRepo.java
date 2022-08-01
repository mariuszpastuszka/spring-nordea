package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;
import java.util.Set;


public class StubCriminalCaseRepo extends StubAbstractRepo<CriminalCase> implements CriminalCaseRepo {

    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
