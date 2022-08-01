
package com.mpas.cems.dj.sandbox.repos;

import com.mpas.cems.dao.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface PersonRepo extends Repository<Person, Long> {
    Optional<Person> findById(Long id);

    List<Person> findAll();

    void save(Person person);
}
