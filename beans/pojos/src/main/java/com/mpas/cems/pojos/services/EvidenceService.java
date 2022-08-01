package com.mpas.cems.pojos.services;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;


public interface EvidenceService extends AbstractService<Evidence> {
    Evidence createEvidence(CriminalCase criminalCase, Storage storage, String itemName);
}
