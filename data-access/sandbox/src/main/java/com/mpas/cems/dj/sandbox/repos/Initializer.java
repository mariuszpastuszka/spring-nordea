
package com.mpas.cems.dj.sandbox.repos;

import com.mpas.cems.dao.*;
import com.mpas.cems.dj.sandbox.repos.cc.CriminalCaseRepo;
import com.mpas.cems.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Service
public class Initializer {

    private Logger logger = LoggerFactory.getLogger(Initializer.class);
    private PersonRepo personRepo;
    private DetectiveRepo detectiveRepo;
    private StorageRepo storageRepo;
    private CriminalCaseRepo criminalCaseRepo;

    public Initializer(PersonRepo personRepo, DetectiveRepo detectiveRepo, StorageRepo storageRepo, CriminalCaseRepo criminalCaseRepo) {
        this.personRepo = personRepo;
        this.detectiveRepo = detectiveRepo;
        this.storageRepo = storageRepo;
        this.criminalCaseRepo = criminalCaseRepo;
    }

    @PostConstruct
    public void init() {
        logger.info(" -->> Starting database initialization...");
        var person = new Person();
        person.setUsername("sherlock.holmes");
        person.setFirstName("Sherlock");
        person.setLastName("Holmes");
        person.setPassword("dududu");
        person.setHiringDate(LocalDateTime.now());
        personRepo.save(person);
        var detective = createDetective(person, Rank.SENIOR, false, EmploymentStatus.ACTIVE);
        detectiveRepo.save(detective);

        person = new Person();
        person.setUsername("jackson.brodie");
        person.setFirstName("Jackson");
        person.setLastName("Brodie");
        person.setPassword("bagyrf");
        person.setHiringDate(LocalDateTime.now());
        personRepo.save(person);
        var detective2 = createDetective(person, Rank.INSPECTOR, true, EmploymentStatus.ACTIVE);
        detectiveRepo.save(detective2);

        initStorages();

        var storage = new Storage();
        storage.setName("Mancester Central");
        storage.setLocation("MC1 2RC");
        storageRepo.save(storage);

        createCase(detective, detective2, storage);
        logger.info(" -->> Database initialization finished.");
    }

    private void createCase(Detective detective, Detective detective2, Storage storage) {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setNumber("OM51710783");
        criminalCase.setType(CaseType.FELONY);
        criminalCase.setShortDescription("White female stabbed 13 times.");
        criminalCase.setStatus(CaseStatus.UNDER_INVESTIGATION);
        criminalCase.setNotes("It is obvious the husband did it.");
        criminalCase.setLeadInvestigator(detective);
        criminalCase.addDetective(detective2);

        Evidence evidence = new Evidence();
        evidence.setStorage(storage);
        evidence.setCriminalCase(criminalCase);
        evidence.setNumber(NumberGenerator.getEvidenceNumber());
        evidence.setItemName("Bloody Knife");
        evidence.setNotes("The printed of the husband were on the bloody knife.");
        evidence.setArchived(true);
        evidence.addTrackEntry(createTrackEntry(detective, TrackAction.SUBMITTED,"Submitting bloody knife as evidence."));
        evidence.addTrackEntry(createTrackEntry(detective, TrackAction.RETRIEVED,"Forensic Analysis - look for prints."));
        evidence.addTrackEntry(createTrackEntry(detective, TrackAction.RETURNED,"Forensic Analysis completed."));

        criminalCase.addEvidence(evidence);
        criminalCaseRepo.save(criminalCase);
    }

    private void initStorages() {
        createStorage("Central London", "NW1 6XE");
        createStorage("South London", "SW4 5XE");
        createStorage("Central Edinburgh", "EH2 7DE");
        createStorage("North Birmingham", "NB4 9RF");
        createStorage("Central Newcastle Upon Thyne", "CN4 9UT");
        createStorage("Central Fife", "CF4 9EH");
        createStorage("Central Stirling", "CS1 6SC");
        createStorage("South Falkirk", "SF4 5FS");
        createStorage("Central Ayr", "CA2 7DA");
        createStorage("North Cardiff", "NC4 5NA");
        createStorage("East Devon", "DE4 2ED");
        createStorage("Central Sussex", "CS4 7XC");
    }

    private Detective createDetective(final Person person, final Rank rank, final Boolean armed,final EmploymentStatus status){
        var detective = new Detective();
        detective.setPerson(person);
        detective.setBadgeNumber(NumberGenerator.getBadgeNumber());
        detective.setRank(rank);
        detective.setArmed(armed);
        detective.setStatus(status);
        return detective;
    }

    private void createStorage(final String name, final String address) {
        var storage = new Storage();
        storage.setName(name);
        storage.setLocation(address);
        storageRepo.save(storage);
    }

    private TrackEntry createTrackEntry(Detective detective, TrackAction action, String reason) {
        TrackEntry trackEntry = new TrackEntry();
        trackEntry.setDate(LocalDateTime.now());
        trackEntry.setDetective(detective);
        trackEntry.setAction(action);
        trackEntry.setReason(reason);
        return trackEntry;
    }
}
