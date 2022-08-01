
package com.mpas.cems.dj.services.impl;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.dj.repos.TrackEntryRepo;
import com.mpas.cems.dj.services.TrackEntryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class TrackEntryServiceImpl implements TrackEntryService {

    private TrackEntryRepo trackEntryRepo;

    public TrackEntryServiceImpl(TrackEntryRepo trackEntryRepo) {
        this.trackEntryRepo = trackEntryRepo;
    }

    @Override
    public List<TrackEntry> findByDate(LocalDateTime localDate) {
        return trackEntryRepo.findByDate(localDate);
    }

    @Override
    public List<TrackEntry> findByEvidence(Evidence evidence) {
        return trackEntryRepo.findByEvidence(evidence);
    }

    @Override
    public List<TrackEntry> findByDetective(Detective detective) {
        return trackEntryRepo.findByDetective(detective);
    }

    @Override
    public TrackEntry save(TrackEntry entry) {
        return trackEntryRepo.save(entry);
    }
}
