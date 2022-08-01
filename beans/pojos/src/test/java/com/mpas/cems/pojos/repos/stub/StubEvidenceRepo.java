package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Set;


public class StubEvidenceRepo extends StubAbstractRepo<Evidence> implements EvidenceRepo {
    @Override
    public Set<Evidence> findByCriminalCase(CriminalCase criminalCase) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Evidence findByNumber(String evidenceNumber) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public boolean isInStorage(Storage storage) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
