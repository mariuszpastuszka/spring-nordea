
package com.mpas.cems.hib.repos;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SuppressWarnings("unchecked")
@Repository("hibernatePersonRepo")
public class HibernateRepo implements PersonRepo {

    private SessionFactory sessionFactory;

    public HibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Returns the session associated with the ongoing reward transaction.
     *
     * @return the transactional session
     */
    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Set<Person> findAll() {
        List persons = session().createQuery("FROM Person").list();
        return persons.isEmpty()? Set.of() : new HashSet<>(persons);
    }

    @Override
    public List<Person> findAllByLastName(String lastName) {
        return  (List<Person>) session()
                .createQuery("from Person p where p.lastName= ?1")
                .setParameter(1, lastName).list();
    }

    @Override
    public List<String> findAllUsernames() {
        NativeQuery<String> nq = session()
                .createNativeQuery("select USERNAME from PERSON");
        return nq.getResultList();
    }

    @Override
    public Optional<Person> findById(Long entityId) {
        Person person = session().get(Person.class, entityId);
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public Optional<Person> findByUsername(String username) {
       /* Person person = (Person) session()
                .createQuery("from Person p where p.username= ?1")
                .setParameter(1, username).uniqueResult();
        return person == null? Optional.empty() :Optional.of(person);*/

        Person person = (Person) session()
                .createQuery("from Person p where p.username= :un")
                .setParameter("un", username).uniqueResult();
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        Person person = (Person) session()
                .createQuery("from Person p where p.firstName=?1 and p.lastName=?2")
                .setParameter(1, firstName)
                .setParameter(2, lastName)
                .uniqueResult();
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public int updatePassword(Long personId, String newPass) {
        Person person = session().get(Person.class, personId);
        if(person != null) {
            person.setPassword(newPass);
            session().update(person);
        }
        return 0;
    }

    @Override
    public long count() {
        return (Long) session().createQuery("select count(p) from Person p").uniqueResult();
    }

    @Override
    public void save(Person person) {
        session().persist(person);
    }

    @Override
    public void delete(Person person) {
        session().delete(person);
    }

    @Override
    public Person update(Person person) {
        session().update(person);
        return person;
    }

    @Override
    public int deleteById(Long entityId) {
        Person person = session().get(Person.class, entityId);
        session().delete(person);
        return 1;
    }
}
