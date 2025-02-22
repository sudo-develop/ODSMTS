package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.Inventory;
import com.ODSMTS.Controller.utils.InventoryRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class InventoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final InventoryRowMapper rowMapper = new InventoryRowMapper();

    public InventoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Inventory inventory) {
        String sql = "INSERT INTO inventory (hospital_id, drug_id, drug_form_id, expiry_date, " +
                     "is_expired, is_consumed, current_hospital_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            inventory.getHospitalId(),
            inventory.getDrugId(),
            inventory.getDrugFormId(),
            inventory.getExpiryDate(),
            inventory.getExpired(),
            inventory.getConsumed(),
            inventory.getCurrentHospitalId()
        );
    }

    public Inventory findById(Long id) {
        String sql = "SELECT * FROM inventory WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Inventory> findAll() {
        String sql = "SELECT * FROM inventory";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void update(Inventory inventory) {
        String sql = "UPDATE inventory SET hospital_id = ?, drug_id = ?, drug_form_id = ?, " +
                     "expiry_date = ?, is_expired = ?, is_consumed = ?, current_hospital_id = ? " +
                     "WHERE id = ?";
        jdbcTemplate.update(
            sql,
            inventory.getHospitalId(),
            inventory.getDrugId(),
            inventory.getDrugFormId(),
            inventory.getExpiryDate(),
            inventory.getExpired(),
            inventory.getConsumed(),
            inventory.getCurrentHospitalId(),
            inventory.getId()
        );
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM inventory WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}