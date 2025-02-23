package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.DTO.InventoryDetailsDTO;
import com.ODSMTS.Controller.Entity.Inventory;
import com.ODSMTS.Controller.utils.InventoryDetailsRowMapper;
import com.ODSMTS.Controller.utils.InventoryRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.PreparedStatement;
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


    public Integer saveDrug(Inventory inventory) {
        String sql = "INSERT INTO inventory (hospital_id, drug_id, drug_form_id, expiry_date, " +
                    "is_expired, is_consumed, current_hospital_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, inventory.getHospitalId());  // Changed to setLong
                ps.setLong(2, inventory.getDrugId());      // Changed to setLong
                ps.setLong(3, inventory.getDrugFormId());  // Changed to setLong
                ps.setDate(4, java.sql.Date.valueOf(inventory.getExpiryDate())); 
                ps.setBoolean(5, inventory.getExpired());
                ps.setBoolean(6, inventory.getConsumed());
                ps.setLong(7, inventory.getCurrentHospitalId());  // Changed to setLong
                return ps;
            }, keyHolder);

    return keyHolder.getKey().intValue(); // Return generated inventory ID
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

    private final InventoryDetailsRowMapper detailsRowMapper = new InventoryDetailsRowMapper();

    public List<InventoryDetailsDTO> findInventoryDetailsByHospitalId(Long hospitalId) {
        String sql = 
            "SELECT " +
            "  hs.name AS hospital_name, " +
            "  od.drug_name AS drug_name, " +
            "  df.form_name AS form_name, " +
            "  od.quantity_per_unit AS quantity_per_unit, " +
            "  iv.expiry_date AS expiry_date, " +
            "  COUNT(*) AS count " +
            "FROM inventory iv " +
            "LEFT JOIN hospitals hs ON iv.current_hospital_id = hs.id " +
            "LEFT JOIN orphan_drugs od ON iv.drug_id = od.id " +
            "LEFT JOIN drug_form df ON iv.drug_form_id = df.id " +
            "WHERE iv.hospital_id = ? " +
            "GROUP BY hs.name, od.drug_name, df.form_name, od.quantity_per_unit, iv.expiry_date";
    
        return jdbcTemplate.query(sql, detailsRowMapper, hospitalId);
    }

}