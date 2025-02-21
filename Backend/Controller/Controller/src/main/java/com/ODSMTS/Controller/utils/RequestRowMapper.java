package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.Entity.Request;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RequestRowMapper implements RowMapper<Request> {
    @Override
    public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
        Request request = new Request();
        request.setId(rs.getLong("id"));
        request.setDrugId(rs.getLong("drug_id"));
        request.setDrugFormId(rs.getLong("drug_form_id"));
        request.setCreatedBy(rs.getLong("created_by"));
        
        long fulfilledBy = rs.getLong("fulfilled_by");
        request.setFulfilledBy(rs.wasNull() ? null : fulfilledBy);
        
        request.setQuantity(rs.getInt("quantity"));
        request.setFulfilledQuantity(rs.getInt("fulfilled_quantity"));
        request.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime());
        request.setStatus(Request.RequestStatus.valueOf(rs.getString("status")));
        return request;
    }
}