package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.Entity.Role;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setRole(rs.getString("Roles"));  // Maps to the "Roles" column
        return role;
    }
}