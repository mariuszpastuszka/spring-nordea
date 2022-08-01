package com.mpas.cems.pojos.repos.stub;

import com.mpas.cems.dao.Person;
import com.mpas.cems.pojos.repos.PersonRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Set;


public class StubPersonRepo extends StubAbstractRepo<Person> implements PersonRepo {
    @Override
    public Person findByUsername(String username) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Person> findByCompleteName(String firstName, String lastName) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
