package com.mpas.cems.pojos.services;

import com.mpas.cems.pojos.repos.CriminalCaseRepo;
import com.mpas.cems.pojos.repos.DetectiveRepo;
import com.mpas.cems.pojos.repos.EvidenceRepo;
import com.mpas.cems.pojos.repos.StorageRepo;
import com.mpas.cems.pojos.repos.stub.StubCriminalCaseRepo;
import com.mpas.cems.pojos.repos.stub.StubDetectiveRepo;
import com.mpas.cems.pojos.repos.stub.StubEvidenceRepo;
import com.mpas.cems.pojos.repos.stub.StubStorageRepo;
import com.mpas.cems.pojos.services.impl.SimpleDetectiveService;
import com.mpas.cems.pojos.services.impl.SimpleOperationsService;
import com.mpas.cems.pojos.services.impl.SimpleStorageService;


public class SimpleServiceTestBase {
    DetectiveRepo detectiveRepo;
    CriminalCaseRepo criminalCaseRepo;
    EvidenceRepo evidenceRepo;
    StorageRepo storageRepo;

    OperationsService operationsService;
    SimpleDetectiveService detectiveService;
    SimpleStorageService storageService;

    void init() {
        detectiveRepo = new StubDetectiveRepo();
        criminalCaseRepo = new StubCriminalCaseRepo();
        evidenceRepo = new StubEvidenceRepo();
        storageRepo = new StubStorageRepo();

        operationsService = new SimpleOperationsService();

        operationsService.setDetectiveRepo(detectiveRepo);
        operationsService.setCriminalCaseRepo(criminalCaseRepo);
        operationsService.setEvidenceRepo(evidenceRepo);
        operationsService.setStorageRepo(storageRepo);

        detectiveService = new SimpleDetectiveService();
        detectiveService.setRepo(detectiveRepo);
        storageService = new SimpleStorageService();
        storageService.setRepo(storageRepo);
    }

}
