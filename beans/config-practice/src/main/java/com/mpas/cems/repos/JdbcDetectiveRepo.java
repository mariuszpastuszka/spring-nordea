package com.mpas.cems.repos;

import com.mpas.cems.dao.Detective;
import com.mpas.cems.pojos.repos.DetectiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;


@Component
public class JdbcDetectiveRepo extends JdbcAbstractRepo<Detective> implements DetectiveRepo {

    public JdbcDetectiveRepo() {
    }

    @Autowired
    public JdbcDetectiveRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Optional<Detective> findByBadgeNumber(String badgeNumber) {
        return Optional.empty();
    }
}
