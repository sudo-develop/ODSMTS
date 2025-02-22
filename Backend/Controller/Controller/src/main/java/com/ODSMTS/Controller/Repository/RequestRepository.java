package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.Request;
import com.ODSMTS.Controller.utils.RequestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class RequestRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Request> rowMapper = new RequestRowMapper();

    public RequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create
    public void save(Request request) {
        String sql = "INSERT INTO requests (drug_id, drug_form_id, created_by, fulfilled_by, quantity, fulfilled_quantity, request_date, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            request.getDrugId(),
            request.getDrugFormId(),
            request.getCreatedBy(),
            request.getFulfilledBy(),
            request.getQuantity(),
            request.getFulfilledQuantity(),
            request.getRequestDate(),
            request.getStatus().name()
        );
    }

    // Read
    public Request findById(Long id) {
        String sql = "SELECT * FROM requests WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Request> findAll() {
        String sql = "SELECT * FROM requests";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Update
    public void update(Request request) {
        String sql = "UPDATE requests SET drug_id = ?, drug_form_id = ?, fulfilled_by = ?, quantity = ?, " +
                     "fulfilled_quantity = ?, request_date = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(
            sql,
            request.getDrugId(),
            request.getDrugFormId(),
            request.getFulfilledBy(),
            request.getQuantity(),
            request.getFulfilledQuantity(),
            request.getRequestDate(),
            request.getStatus().name(),
            request.getId()
        );
    }

    // Delete
    public void deleteById(Long id) {
        String sql = "DELETE FROM requests WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}