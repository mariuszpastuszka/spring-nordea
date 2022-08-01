package com.mpas.cems.dj.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findByLeadInvestigator(Detective detective);
}
