package com.mpas.cems.boot.entities;

import javax.persistence.*;
import java.util.Objects;


@Entity
@NamedQueries({
        @NamedQuery(name = Person.FIND_ALL, query = "select p from Person p"),
        @NamedQuery(name = Person.FIND_BY_ID, query = "select p from Person p where p.id=:id"),
        @NamedQuery(name = Person.COUNT_ALL, query = "select count(p) from Person p")
})
public class Person {

    public static final String FIND_ALL = "Person.findAll";
    public static final String FIND_BY_ID = "Person.findById";
    public static final String COUNT_ALL = "Person.countAll";

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    protected Long id;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.getFirstName()) &&
                Objects.equals(lastName, person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("Person[username='%s%n', firstName='%s', lastName='%s']"
                , firstName, lastName);

    }
}
