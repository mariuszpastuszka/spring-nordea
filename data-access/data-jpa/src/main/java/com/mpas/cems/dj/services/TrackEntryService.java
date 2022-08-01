package com.mpas.cems.dj.services;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.TrackEntry;

import java.time.LocalDateTime;
import java.util.List;


public interface TrackEntryService {

    List<TrackEntry> findByDate(LocalDateTime localDate);

    List<TrackEntry> findByEvidence(Evidence evidence);

    List<TrackEntry> findByDetective(Detective detective);

    TrackEntry save(TrackEntry entry);
}
