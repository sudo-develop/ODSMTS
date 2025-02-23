package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.Role;
import com.ODSMTS.Controller.utils.RoleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RoleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RoleRowMapper rowMapper = new RoleRowMapper();

    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create
    public void save(Role role) {
        String sql = "INSERT INTO roles (Roles) VALUES (?)";
        jdbcTemplate.update(sql, role.getRole());
    }

    // Read
    public Role findById(Long id) {
        String sql = "SELECT * FROM roles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Role> findAll() {
        String sql = "SELECT * FROM roles";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Update
    public void update(Role role) {
        String sql = "UPDATE roles SET Roles = ? WHERE id = ?";
        jdbcTemplate.update(sql, role.getRole(), role.getId());
    }

    // Delete
    public void deleteById(Long id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}