package com.mpas.cems.pojos.services;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.util.CaseStatus;
import com.mpas.cems.util.CaseType;

import java.util.Set;


public interface CriminalCaseService extends AbstractService<CriminalCase> {
    CriminalCase createCriminalCase(CaseType type, String shortDescription, String detailedDescription, CaseStatus caseStatus, String notes, Set<Evidence> evidenceSet, Detective leadInvestigator);
}
