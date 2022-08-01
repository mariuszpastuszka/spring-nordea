
package com.mpas.cems.dj.repos;

import com.mpas.cems.dao.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// TODO 42. Complete the definition of this interface to make the tests in PersonServiceTest.java pass.
public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);
    Optional<Person> findByCompleteName(String firstName, String lastName);
}
