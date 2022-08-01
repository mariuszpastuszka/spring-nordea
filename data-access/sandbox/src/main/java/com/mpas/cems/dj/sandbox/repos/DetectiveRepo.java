
package com.mpas.cems.dj.sandbox.repos;

import com.mpas.cems.dao.Detective;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass = Detective.class, idClass = Long.class)
public interface DetectiveRepo {
    Optional<Detective> findById(Long id);

    List<Detective> findAll();

    void save(Detective detective);
}
