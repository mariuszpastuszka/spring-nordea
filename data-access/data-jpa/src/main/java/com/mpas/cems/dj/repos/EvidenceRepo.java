
package com.mpas.cems.dj.repos;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Evidence;
import com.mpas.cems.dao.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EvidenceRepo extends JpaRepository<Evidence,Long> {
    
    List<Evidence> findAllByStorage(Storage storage);

    List<Evidence> findAllByCriminalCase(CriminalCase criminalCase);
}
