
package com.mpas.cems.dj.services;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;

import java.util.List;


public interface EvidenceService {

    List<Evidence> findAll();

    List<Evidence> findAllByStorage(Storage storage);

    List<Evidence> findAllByCriminalCase(CriminalCase criminalCase);

    Evidence save(Evidence evidence);
}
