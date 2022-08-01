package com.mpas.cems.repos.util;

import com.mpas.cems.dao.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StorageRowMapper implements RowMapper<Storage> {
    @Override
    public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getLong("ID");
        var name = rs.getString("NAME");
        var location = rs.getString("LOCATION");

        var storage = new Storage();
        storage.setId(id);
        storage.setName(name);
        storage.setLocation(location);
        return storage;
    }
}
