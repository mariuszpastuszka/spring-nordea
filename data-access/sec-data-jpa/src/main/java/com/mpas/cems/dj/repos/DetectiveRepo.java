
package com.mpas.cems.dj.repos;

import com.mpas.cems.dao.Detective;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface DetectiveRepo extends JpaRepository<Detective, Long> {

    @EntityGraph(attributePaths = {"criminalCases"})
    @Query("select d from Detective d where d.id=:id" )
    Optional<Detective> findByIdWithCriminalCases(@Param("id") Long id);
}
