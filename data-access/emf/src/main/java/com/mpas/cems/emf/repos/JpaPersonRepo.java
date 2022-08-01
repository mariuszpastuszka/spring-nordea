
package com.mpas.cems.emf.repos;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SuppressWarnings("unchecked")
@Repository("jpaPersonRepo")
public class JpaPersonRepo implements PersonRepo {

    private EntityManager entityManager;

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Person> findAll() {
        List persons = (List<Person>) entityManager.createQuery("select p from Person p").getResultList();
        return persons.isEmpty()? Set.of() : new HashSet<>(persons);
    }

    @Override
    public List<Person> findAllByLastName(String lastName) {
        //create the query
        CriteriaBuilder builder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);
        ParameterExpression<String> value = builder.parameter(String.class);
        query.select(personRoot).where(builder.equal(personRoot.get("lastName"), value));

        // execute the query
        TypedQuery<Person> tquery = entityManager.createQuery(query);
        tquery.setParameter(value,lastName);
        return tquery.getResultList();

       /* return  (List<Person>) entityManager
                .createQuery(Person.FIND_BY_LAST_NAME)
                .setParameter(1, lastName).getResultList();*/
    }

    @Override
    public List<String> findAllUsernames() {
        Query nq = entityManager
                .createNativeQuery("select USERNAME from PERSON");
        return (List<String>) nq.getResultList();
    }

    @Override
    public Optional<Person> findById(Long entityId) {
        Person person = entityManager.find(Person.class, entityId);
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        Person person = (Person) entityManager
                .createQuery("from Person p where p.username= ?1")
                .setParameter(1, username).getSingleResult();
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        /*
        Person person = (Person) entityManager
                .createQuery("from Person p where p.firstName=?1 and p.lastName=?2")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .getSingleResult();
        */
        Person person = (Person) entityManager
                .createNamedQuery(Person.FIND_BY_COMPLETE_NAME)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public int updatePassword(Long personId, String newPass) {
        Person person = entityManager.find(Person.class, personId);
        if(person != null) {
            person.setPassword(newPass);
            entityManager.merge(person);
        }
        return 0;
    }

    @Override
    public long count() {
        return (Long) entityManager.createQuery("select count(p) from Person p").getSingleResult();
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void delete(Person person) {
        entityManager.remove(person);
    }

    @Override
    public Person update(Person person) {
        entityManager.merge(person);
        return person;
    }

    @Override
    public int deleteById(Long entityId) {
        Person person = entityManager.find(Person.class, entityId);
        entityManager.remove(person);
        return 1;
    }
}
