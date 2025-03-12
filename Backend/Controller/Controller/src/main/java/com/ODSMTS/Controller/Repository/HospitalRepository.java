package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.Hospital;
import com.ODSMTS.Controller.utils.HospitalRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HospitalRepository {
    private final JdbcTemplate jdbcTemplate;
    private final HospitalRowMapper rowMapper = new HospitalRowMapper();

    public HospitalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Hospital hospital) {
        String sql = "INSERT INTO hospitals (name, address, contact_number, pincode, city, state) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            hospital.getName(),
            hospital.getAddress(),
            hospital.getContactNumber(),
            hospital.getPincode(),
            hospital.getCity(),
            hospital.getState()
        );
    }

    public Hospital findById(Integer id) {
        String sql = "SELECT * FROM hospitals WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Hospital> findAll() {
        String sql = "SELECT * FROM hospitals";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void update(Hospital hospital) {
        String sql = "UPDATE hospitals SET name = ?, address = ?, contact_number = ?, pincode = ?, " +
                     "city = ?, state = ? WHERE id = ?";
        jdbcTemplate.update(
            sql,
            hospital.getName(),
            hospital.getAddress(),
            hospital.getContactNumber(),
            hospital.getPincode(),
            hospital.getCity(),
            hospital.getState(),
            hospital.getId()
        );
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM hospitals WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}