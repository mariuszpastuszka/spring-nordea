
package com.mpas.cems.mongo.repos;

import com.mpas.cems.mongo.dao.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;
import java.util.List;


public interface PersonRepo extends MongoRepository<Person, BigInteger> {

    @Query("{'lastName': { '$regex' : ?0 } }")
    List<Person> findByLastName(String lastName);

    @Query("{ 'username' :  ?0 }")
    Person findByUsername(String username);

    void deleteAll();
}
