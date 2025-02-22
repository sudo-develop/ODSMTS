package com.ODSMTS.Controller.utils;


import com.ODSMTS.Controller.Entity.OrphanDrug;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrphanDrugRowMapper implements RowMapper<OrphanDrug> {
    @Override
    public OrphanDrug mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrphanDrug drug = new OrphanDrug();
        drug.setId(rs.getLong("id"));
        drug.setDrugName(rs.getString("drug_name"));
        drug.setStandardPackageUnit(rs.getString("standard_package_unit"));
        drug.setQuantityPerUnit(rs.getInt("quantity_per_unit"));
        drug.setShelfLife(rs.getString("shelf_life"));
        return drug;
    }
}
