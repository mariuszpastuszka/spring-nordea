package com.mpas.cems.repos2;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import com.mpas.cems.repos.JdbcAbstractRepo;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;


@Component
public class JPACriminalCaseRepo extends JdbcAbstractRepo<CriminalCase> implements CriminalCaseRepo {
    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        throw new NotImplementedException("Not needed for this scenario");
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        throw new NotImplementedException("Not needed for this scenario");
    }
}
