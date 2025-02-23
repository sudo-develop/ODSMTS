package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.DTO.InventoryDetailsDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class InventoryDetailsRowMapper implements RowMapper<InventoryDetailsDTO> {
    @Override
    public InventoryDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        InventoryDetailsDTO dto = new InventoryDetailsDTO();
        dto.setHospitalName(rs.getString("hospital_name"));
        dto.setDrugName(rs.getString("drug_name"));
        dto.setFormName(rs.getString("form_name"));
        dto.setQuantityPerUnit(rs.getInt("quantity_per_unit"));
        dto.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
        dto.setCount(rs.getInt("count")); // Map the COUNT(*) result
        return dto;
    }
}