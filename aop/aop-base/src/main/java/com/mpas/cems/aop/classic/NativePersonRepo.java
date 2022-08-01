package com.mpas.cems.aop.classic;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import org.apache.commons.lang3.NotImplementedException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class NativePersonRepo implements PersonRepo {

    private DataSource dataSource;

    @Override
    public long count() {
        throw new NotImplementedException("Not needed for this scenario.");
    }

    public NativePersonRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public Person update(Person entity) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public int updatePassword(Long personId, String newPass) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public Set<Person> findAll() {
        Set<Person> persons;
        var sql = "select p.ID as ID, p.USERNAME as USERNAME," +
                " p.FIRSTNAME as FIRSTNAME, p.LASTNAME as LASTNAME, p.HIRINGDATE as HIRINGDATE" +
                " from PERSON p ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            persons = mapPersons(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Person not found!", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return persons;
    }

    @Override
    public void save(Person entity) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public void delete(Person entity) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public int deleteById(Long entityId) {
        throw new NotImplementedException("Not needed for this example");
    }

    @Override
    public Optional<Person> findById(Long id) {
        var sql = "select p.ID as ID, p.USERNAME as USERNAME," +
                " p.FIRSTNAME as FIRSTNAME, p.LASTNAME as LASTNAME, p.HIRINGDATE as HIRINGDATE" +
                " from PERSON p where p.ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            Set<Person> persons = mapPersons(rs);
            if (!persons.isEmpty()) {
                return Optional.of(persons.iterator().next());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Person not found!", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return Optional.empty();
    }


    private Set<Person> mapPersons(ResultSet rs) throws SQLException {
        Set<Person> persons = new HashSet<>();
        Person person;
        while (rs.next()) {
            person = new Person();
            // set internal entity identifier (primary key)
            person.setId(rs.getLong("ID"));
            person.setUsername(rs.getString("USERNAME"));
            person.setFirstName(rs.getString("FIRSTNAME"));
            person.setLastName(rs.getString("LASTNAME"));
            person.setHiringDate(rs.getTimestamp("HIRINGDATE").toLocalDateTime());
            persons.add(person);
        }
        return persons;
    }
}
