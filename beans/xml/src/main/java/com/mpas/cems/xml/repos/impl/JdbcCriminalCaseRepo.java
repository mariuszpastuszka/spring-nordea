package com.mpas.cems.xml.repos.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.pojos.repos.CriminalCaseRepo;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Set;


public class JdbcCriminalCaseRepo extends JdbcAbstractRepo<CriminalCase> implements CriminalCaseRepo {

    public JdbcCriminalCaseRepo() {
    }

    public JdbcCriminalCaseRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Set<CriminalCase> findByLeadInvestigator(Detective detective) {
        return null;
    }

    @Override
    public Optional<CriminalCase> findByNumber(String caseNumber) {
        return Optional.empty();
    }
}
