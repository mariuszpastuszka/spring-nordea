package com.mpas.cems.dj.repos;

import com.mpas.cems.dao.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
}
