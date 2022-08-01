package com.mpas.cems.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;

import java.util.Optional;
import java.util.Set;


public interface EvidenceRepo extends AbstractRepo<Evidence> {

    Set<Evidence> findByCriminalCase(CriminalCase criminalCase);

    Optional<Evidence> findByNumber(String evidenceNumber);

    boolean isInStorage(Storage storage);
}
