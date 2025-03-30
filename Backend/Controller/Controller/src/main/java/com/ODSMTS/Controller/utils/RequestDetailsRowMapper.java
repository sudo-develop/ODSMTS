package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.DTO.RequestDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDetailsRowMapper implements RowMapper<RequestDTO> {
    @Override
    public RequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RequestDTO request = new RequestDTO();
        request.setRequestId(rs.getLong("id"));
        request.setcreatedBy(rs.getLong("created_by"));
        request.setHospitalName(rs.getString("hospital_name"));
        request.setDrugName(rs.getString("drug_name"));
        request.setFormName(rs.getString("form_name"));
        request.setQuantity(rs.getInt("quantity"));
        request.setFulfilledBy(rs.getLong("fulfilled_by")); // ✅ Keeps the ID
        request.setFulfilledByName(rs.getString("fulfilled_by_name")); // ✅ Adds the name
        request.setFulfilledQuantity(rs.getInt("fulfilled_quantity"));
        request.setStatus(rs.getString("status"));

        if (rs.getTimestamp("request_date") != null) {
            request.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime());
        }

        return request;
    }
}
