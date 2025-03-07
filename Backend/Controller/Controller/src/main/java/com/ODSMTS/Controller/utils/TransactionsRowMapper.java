package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.Entity.Transactions;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransactionsRowMapper implements RowMapper<Transactions> {
    @Override
    public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transactions transaction = new Transactions();
        transaction.setId(rs.getLong("id"));
        transaction.setFromHospitalId(rs.getLong("from_hospital_id"));
        transaction.setToHospitalId(rs.getLong("to_hospital_id"));
        transaction.setRequestId(rs.getLong("request_id"));
        transaction.setGivenCount(rs.getInt("given_count"));

        // Handling SQL Timestamp conversion to LocalDateTime
        if (rs.getTimestamp("created_at") != null) {
            transaction.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }

        return transaction;
    }
}
