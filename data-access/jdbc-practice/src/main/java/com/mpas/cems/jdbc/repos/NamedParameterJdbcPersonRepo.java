
package com.mpas.cems.jdbc.repos;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.PersonRepo;
import com.mpas.cems.repos.util.PersonRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;


@Repository("jdbcNamedPersonRepo")
// TODO 28. Some of the methods have incomplete bodies that need to be completed with appropriate NamedParameterJdbcTemplate instance calls.
public class NamedParameterJdbcPersonRepo implements PersonRepo {

    private RowMapper<Person> rowMapper = new PersonRowMapper();

    private NamedParameterJdbcTemplate jdbcNamedTemplate;

    public NamedParameterJdbcPersonRepo(NamedParameterJdbcTemplate jdbcNamedTemplate) {
        this.jdbcNamedTemplate = jdbcNamedTemplate;
    }

    @Override
    public Optional<Person> findById(Long entityId) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where ID= :id";
        // add NamedParameterJdbcTemplate instance call to find a person
        return Optional.empty();
    }

    @Override
    public int createPerson(Long entityId, String username, String firstName, String lastName, String password) {
        Map<String, Object> params = Map.of(
                "id", entityId,
                "un", username,
                "fn", firstName,
                "ln", lastName,
                "password", password,
                "hd", LocalDateTime.now(),
                "createdAt", LocalDateTime.now(),
                "modifiedAt", LocalDateTime.now(),
                "version", 1
        );
        return jdbcNamedTemplate.update(
                "insert into PERSON(ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE, CREATED_AT, MODIFIED_AT, VERSION) " +
                        "values(:id, :un, :fn, :ln, :password, :hd, :createdAt, :modifiedAt, :version )",
                params);
    }

    @Override
    public int updatePassword(Long personId, String newPass) {
        // add NamedParameterJdbcTemplate instance call to update the password for a person with ID= personId
        return 0;
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where USERNAME= :un";
        return Optional.of(jdbcNamedTemplate.queryForObject(sql, Map.of("un", username) ,rowMapper));
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String lastName) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where FIRSTNAME= :fn and LASTNAME= :ln";
        return Optional.of(jdbcNamedTemplate.queryForObject(sql, Map.of("fn", firstName, "ln", lastName) ,rowMapper));
    }

    @Override
    public Set<Person> findAll() {
        String sql = "select * from PERSON";
        return new HashSet<>(jdbcNamedTemplate.query(sql, rowMapper));
    }

    @Override
    public long count() {
        String sql = "select count(*) from PERSON";
        return jdbcNamedTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    @Override
    public void save(Person person) {
        Map<String, Object> params = Map.of(
                "id", person.getId(),
                "un", person.getUsername(),
                "fn", person.getFirstName(),
                "ln", person.getLastName(),
                "password", person.getPassword(),
                "hd", LocalDateTime.now(),
                "createdAt", LocalDateTime.now(),
                "modifiedAt", LocalDateTime.now(),
                "version", 1
        );
        jdbcNamedTemplate.update(
                "insert into PERSON(ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE, CREATED_AT, MODIFIED_AT, VERSION) " +
                        "values(:id, :un, :fn, :ln, :password, :hd, :createdAt, :modifiedAt, :version )",
                params);
    }

    @Override
    public void delete(Person entity) {
        jdbcNamedTemplate.update("delete from p_user where id = :id", Map.of("id", entity.getId()));
    }

    @Override
    public Person update(Person entity) {
        return null;
    }

    @Override
    public int deleteById(Long entityId) {
        // add NamedParameterJdbcTemplate instance call to delete a person
        return 0;    }
}
