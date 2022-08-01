package com.mpas.cems.repos.util;

import com.mpas.cems.dao.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("ID");
        var username = rs.getString("USERNAME");
        var firstname = rs.getString("FIRSTNAME");
        var lastname = rs.getString("LASTNAME");
        var password = rs.getString("PASSWORD");
        var hiringDate = rs.getTimestamp("HIRINGDATE").toLocalDateTime();

        var person = new Person();
        person.setId(id);
        person.setUsername(username);
        person.setFirstName(firstname);
        person.setLastName(lastname);
        person.setPassword(password);
        person.setHiringDate(hiringDate);
        return person;
    }
}
