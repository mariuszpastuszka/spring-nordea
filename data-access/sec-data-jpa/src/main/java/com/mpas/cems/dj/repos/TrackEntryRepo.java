package com.mpas.cems.dj.repos;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.TrackEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface TrackEntryRepo extends JpaRepository<TrackEntry, Long> {
    List<TrackEntry> findByDate(LocalDateTime localDate);

    List<TrackEntry> findByEvidence(Evidence evidence);

    List<TrackEntry> findByDetective(Detective detective);
}
