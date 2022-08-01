package com.mpas.cems.xml.repos.impl;

import com.mpas.cems.dao.TrackEntry;
import com.mpas.cems.pojos.repos.TrackEntryRepo;
import com.mpas.cems.util.TrackAction;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Set;


public class JdbcTrackEntryRepo extends JdbcAbstractRepo<TrackEntry> implements TrackEntryRepo {

    public JdbcTrackEntryRepo() {
    }

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
