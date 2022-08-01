package com.mpas.cems.xml.repos.impl;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.pojos.repos.DetectiveRepo;

import javax.sql.DataSource;
import java.util.Optional;


public class JdbcDetectiveRepo extends JdbcAbstractRepo<Detective> implements DetectiveRepo {

    public JdbcDetectiveRepo() {
    }

    public JdbcDetectiveRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return Optional.empty();
    }
}
