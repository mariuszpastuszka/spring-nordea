
package com.mpas.cems.jdbc.repos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcAgnosticRepo implements AgnosticRepo {

    protected JdbcTemplate jdbcTemplate;

    public JdbcAgnosticRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createTable(String name) {
        jdbcTemplate.execute("create table " + name + " (id integer, name varchar2)" );
        String sql = "select count(*) from " + name;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
