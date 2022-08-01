package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.pojos.repos.TrackEntryRepo;
import com.mpas.cems.util.TrackAction;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Date;
import java.util.Set;


public class StubTrackEntryRepo extends StubAbstractRepo<TrackEntry> implements TrackEntryRepo {

    @Override
    public Set<TrackEntry> findByDetectiveId(Long detectiveId) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<TrackEntry> findByEvidenceId(Long evidenceId) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<TrackEntry> findByDate(Date date) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<TrackEntry> findByDateAndAction(Date date, TrackAction action) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
