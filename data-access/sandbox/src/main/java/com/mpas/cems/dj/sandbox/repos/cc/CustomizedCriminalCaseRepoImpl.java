
package com.mpas.cems.dj.sandbox.repos.cc;

import com.mpas.cems.dao.CriminalCase;
import com.mpas.cems.dao.Detective;
import com.mpas.cems.dao.Person;
import com.mpas.cems.util.EmploymentStatus;
import com.mpas.cems.util.Rank;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


public class CustomizedCriminalCaseRepoImpl implements CustomizedCriminalCaseRepo {

    private JdbcTemplate jdbcTemplate;

    public CustomizedCriminalCaseRepoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Detective> getTeam(CriminalCase criminalCase) {
        List<Detective> detectiveList = new ArrayList<>();
        detectiveList.add(criminalCase.getLeadInvestigator());
        List<Detective> team = jdbcTemplate.query("select d.ID, d.BADGENUMBER, d.RANK, d.ARMED, d.STATUS,d.PERSON_ID, " +
                " p.USERNAME, p.FIRSTNAME, p.LASTNAME, p.HIRINGDATE " +
                " from DETECTIVE d, PERSON p, WORKING_DETECTIVE_CASE wdc" +
                " where d.PERSON_ID=p.ID" +
                " and d.ID = wdc.DETECTIVE_ID " +
                " and wdc.CASE_ID =?", rowMapper, criminalCase.getId());
        if (team != null && !team.isEmpty()) {
            detectiveList.addAll(team);
        }
        return detectiveList;
    }

    private RowMapper<Detective> rowMapper = (rs, i) -> {
        Long id = rs.getLong("ID");
        String badgeNumber = rs.getString("BADGENUMBER");
        String rank = rs.getString("RANK");
        Boolean armed = rs.getBoolean("ARMED");
        String status = rs.getString("STATUS");
        Long personId = rs.getLong("PERSON_ID");

        Person person = new Person();
        person.setId(personId);
        person.setUsername(rs.getString("USERNAME"));
        person.setFirstName(rs.getString("FIRSTNAME"));
        person.setLastName(rs.getString("LASTNAME"));
        person.setHiringDate(rs.getTimestamp("HIRINGDATE").toLocalDateTime());

        Detective detective = new Detective();
        detective.setId(id);
        detective.setPerson(person);
        detective.setBadgeNumber(badgeNumber);
        detective.setRank(Rank.valueOf(rank));
        detective.setArmed(armed);
        detective.setStatus(EmploymentStatus.valueOf(status));

        return detective;
    };

}