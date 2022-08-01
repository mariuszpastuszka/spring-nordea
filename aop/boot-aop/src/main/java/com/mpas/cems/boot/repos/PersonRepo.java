package com.mpas.cems.boot.repos;

import com.mpas.cems.boot.entities.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepo extends CrudRepository<Person, Long> {

}
