package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.Entity.Inventory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InventoryRowMapper implements RowMapper<Inventory> {
    @Override
    public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getLong("id"));
        inventory.setHospitalId(rs.getLong("hospital_id"));
        inventory.setDrugId(rs.getLong("drug_id"));
        inventory.setDrugFormId(rs.getLong("drug_form_id"));
        inventory.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
        inventory.setExpired(rs.getBoolean("is_expired"));
        inventory.setConsumed(rs.getBoolean("is_consumed"));
        inventory.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        inventory.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        inventory.setCurrentHospitalId(rs.getLong("current_hospital_id"));
        return inventory;
    }
}