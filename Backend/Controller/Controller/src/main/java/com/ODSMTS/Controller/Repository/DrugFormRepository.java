package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.DrugForm;
import com.ODSMTS.Controller.utils.DrugFormRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DrugFormRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DrugFormRowMapper rowMapper = new DrugFormRowMapper();

    public DrugFormRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(DrugForm form) {
        String sql = "INSERT INTO drug_form (form_name) VALUES (?)";
        jdbcTemplate.update(sql, form.getFormName());
    }

    public DrugForm findById(Long id) {
        String sql = "SELECT * FROM drug_form WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<DrugForm> findAll() {
        String sql = "SELECT * FROM drug_form";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void update(DrugForm form) {
        String sql = "UPDATE drug_form SET form_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, form.getFormName(), form.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM drug_form WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}