package com.mpas.cems.xml.repos.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.EvidenceRepo;

import javax.sql.DataSource;
import java.util.Set;


public class JdbcEvidenceRepo extends JdbcAbstractRepo<Evidence> implements EvidenceRepo {

    public JdbcEvidenceRepo() {
    }

    public JdbcEvidenceRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Set<Evidence> findByCriminalCase(CriminalCase criminalCase) {
        return null;
    }

    @Override
    public Evidence findByNumber(String evidenceNumber) {
        return null;
    }

    @Override
    public boolean isInStorage(Storage storage) {
        return false;
    }
}
