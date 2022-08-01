package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.pojos.repos.AbstractRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import com.mpas.cems.pojos.services.EvidenceService;
import com.mpas.cems.util.NumberGenerator;


public class SimpleEvidenceService extends SimpleAbstractService<Evidence> implements EvidenceService {
    private EvidenceRepo repo;

    @Override
    public Evidence createEvidence(CriminalCase criminalCase, Storage storage, String itemName) {
        Evidence evidence = new Evidence();
        evidence.setCriminalCase(criminalCase);
        evidence.setNumber(NumberGenerator.getEvidenceNumber());
        evidence.setItemName(itemName);
        evidence.setStorage(storage);
        repo.save(evidence);
        return evidence;
    }

    public void setRepo(EvidenceRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<Evidence> getRepo() {
        return repo;
    }
}
