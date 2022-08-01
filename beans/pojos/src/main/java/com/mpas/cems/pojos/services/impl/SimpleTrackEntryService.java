package com.mpas.cems.pojos.services.impl;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.pojos.repos.AbstractRepo;
import com.mpas.cems.pojos.repos.TrackEntryRepo;
import com.mpas.cems.pojos.services.TrackEntryService;
import com.mpas.cems.util.TrackAction;

import java.time.LocalDateTime;


public class SimpleTrackEntryService extends SimpleAbstractService<TrackEntry> implements TrackEntryService {
    private TrackEntryRepo repo;

    @Override
    public TrackEntry createTrackEntry(Evidence evidence, Detective detective, LocalDateTime date, TrackAction action, String reason) {
        var trackEntry = new TrackEntry();
        trackEntry.setEvidence(evidence);
        trackEntry.setDetective(detective);
        trackEntry.setDate(date);
        trackEntry.setAction(action);
        trackEntry.setReason(reason);
        repo.save(trackEntry);
        return trackEntry;
    }

    public void setRepo(TrackEntryRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<TrackEntry> getRepo() {
        return null;
    }
}
