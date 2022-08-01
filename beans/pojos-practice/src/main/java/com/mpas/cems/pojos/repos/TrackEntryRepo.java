package com.mpas.cems.pojos.repos;

import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.util.TrackAction;

import java.util.Date;
import java.util.Set;


public interface TrackEntryRepo extends AbstractRepo<TrackEntry> {

    Set<TrackEntry> findByDetectiveId(Long detectiveId);

    Set<TrackEntry> findByEvidenceId(Long evidenceId);

    Set<TrackEntry> findByDate(Date date);

    Set<TrackEntry> findByDateAndAction(Date date, TrackAction action);

}
