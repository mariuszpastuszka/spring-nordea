package com.mpas.cems.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Set;


@Repository
public class JdbcEvidenceRepo extends JdbcAbstractRepo<Evidence> implements EvidenceRepo {

    public JdbcEvidenceRepo() {
    }

    @Autowired
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
