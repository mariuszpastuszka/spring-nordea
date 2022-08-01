package com.mpas.cems.repos;

import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.pojos.repos.TrackEntryRepo;
import com.mpas.cems.util.TrackAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Set;


@Repository
public class JdbcTrackEntryRepo extends JdbcAbstractRepo<TrackEntry> implements TrackEntryRepo {

    public JdbcTrackEntryRepo() {
    }

    @Autowired
    public JdbcTrackEntryRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Set<TrackEntry> findByDetectiveId(Long detectiveId) {
        return null;
    }

    @Override
    public Set<TrackEntry> findByEvidenceId(Long evidenceId) {
        return null;
    }

    @Override
    public Set<TrackEntry> findByDate(Date date) {
        return null;
    }

    @Override
    public Set<TrackEntry> findByDateAndAction(Date date, TrackAction action) {
        return null;
    }
}
