package com.mpas.cems.pojos.services;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.util.TrackAction;

import java.time.LocalDateTime;


public interface TrackEntryService extends AbstractService<TrackEntry> {
    TrackEntry createTrackEntry(Evidence evidence, Detective detective, LocalDateTime date, TrackAction action, String reason);
}
