
package com.mpas.cems.dj.sandbox.repos.cc;

import com.mpas.cems.dao.CriminalCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CriminalCaseRepo extends CrudRepository<CriminalCase, Long>, CustomizedCriminalCaseRepo {

    @Query("select c from CriminalCase c where c.number=:no")
    Optional<CriminalCase> findByCaseNumber(@Param("no") String number);

}
