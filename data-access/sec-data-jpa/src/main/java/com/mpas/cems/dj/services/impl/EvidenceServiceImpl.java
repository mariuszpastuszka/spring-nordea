
package com.mpas.cems.dj.services.impl;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import com.mpas.cems.dj.repos.EvidenceRepo;
import com.mpas.cems.dj.services.EvidenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EvidenceServiceImpl implements EvidenceService {

    private EvidenceRepo evidenceRepo;

    public EvidenceServiceImpl(EvidenceRepo evidenceRepo) {
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    public List<Evidence> findAll() {
        return evidenceRepo.findAll();
    }

    @Override
    public List<Evidence> findAllByStorage(Storage storage) {
        return evidenceRepo.findAllByStorage(storage);
    }

    @Override
    public List<Evidence> findAllByCriminalCase(CriminalCase criminalCase) {
        return evidenceRepo.findAllByCriminalCase(criminalCase);
    }

    @Override
    public Evidence save(Evidence evidence) {
        return evidenceRepo.save(evidence);
    }
}
