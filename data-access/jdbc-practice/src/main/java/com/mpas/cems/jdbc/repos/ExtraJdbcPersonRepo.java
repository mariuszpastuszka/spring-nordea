
package com.mpas.cems.jdbc.repos;

import com.mpas.cems.dao.Person;
import com.mpas.cems.repos.impl.JdbcPersonRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository("extraJdbcPersonRepo")
public class ExtraJdbcPersonRepo extends JdbcPersonRepo {

    public ExtraJdbcPersonRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public int createPerson(Long entityId, String username, String firstName, String lastName, String password) {
        return jdbcTemplate.update(
                "insert into PERSON(ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE, CREATED_AT, MODIFIED_AT, VERSION) " +
                        "values(?, ?, ?, ?, ?, ?, ?, ?, ? )",
                entityId, username,firstName,lastName,password, LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(), 1L);
    }

    @Override
    public Set<Person> findAllByUsernamePart(String part) {
        String sql = "select ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE from PERSON where USERNAME like '%' || ? || '%' ";
        return new HashSet<Person>(jdbcTemplate.query(sql, new Object[]{part}, rowMapper));
    }

    @Override
    public Map<String, Object> findByIdAsMap(Long id) {
        String sql = "select * from PERSON where ID= ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public List<Map<String, Object>> findAllAsMaps() {
        String sql = "select * from PERSON";
        return jdbcTemplate.queryForList(sql);
    }

    public void htmlAllByName(String name) {
        String sql = "select * from PERSON where USERNAME= ?";
        jdbcTemplate.query(sql, new HTMLPersonRowCallbackHandler(System.out), name);
    }

    private class HTMLPersonRowCallbackHandler implements RowCallbackHandler {

        private PrintStream out;

        HTMLPersonRowCallbackHandler(PrintStream out) {
            this.out = out;
        }

        @Override
        public void processRow(ResultSet rs) throws SQLException {
            out.print("<p>person ID: ".concat(rs.getLong("ID") + "").concat("</p></br>\n")
                    .concat("<p>username: ").concat(rs.getString("USERNAME")).concat("</p></br>\n")
                    .concat("<p>firstname: ").concat(rs.getString("FIRSTNAME")).concat("</p></br>\n")
                    .concat("<p>lastname: ").concat(rs.getString("LASTNAME")).concat("</p></br>\n"));
        }
    }

}
