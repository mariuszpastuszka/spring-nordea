
package com.mpas.cems.dj.services;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;

import java.util.List;


public interface CriminalCaseService {
    List<CriminalCase> findAll();

    List<CriminalCase> findAllByLeadInvestigator(Detective detective);

    CriminalCase save(CriminalCase criminalCase);
}
