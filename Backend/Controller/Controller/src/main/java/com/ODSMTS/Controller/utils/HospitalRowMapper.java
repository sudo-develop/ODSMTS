package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.Entity.Hospital;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalRowMapper implements RowMapper<Hospital> {
    @Override
    public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getLong("id"));
        hospital.setName(rs.getString("name"));
        hospital.setAddress(rs.getString("address"));
        hospital.setContactNumber(rs.getString("contact_number"));
        hospital.setPincode(rs.getString("pincode"));
        hospital.setCity(rs.getString("city"));
        hospital.setState(rs.getString("state"));
        hospital.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return hospital;
    }
}