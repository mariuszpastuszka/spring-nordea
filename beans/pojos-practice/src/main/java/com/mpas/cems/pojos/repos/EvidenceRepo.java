package com.mpas.cems.pojos.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;

import java.util.Set;


public interface EvidenceRepo extends AbstractRepo<Evidence> {

    Set<Evidence> findByCriminalCase(CriminalCase criminalCase);

    Evidence findByNumber(String evidenceNumber);

    boolean isInStorage(Storage storage);
}
