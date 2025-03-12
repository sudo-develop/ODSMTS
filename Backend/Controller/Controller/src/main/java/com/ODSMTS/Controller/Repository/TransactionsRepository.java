package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.Transactions;
import com.ODSMTS.Controller.utils.TransactionsRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionsRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM transactions";
        return jdbcTemplate.query(sql, new TransactionsRowMapper());
    }

    public Transactions getTransactionById(Long id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new TransactionsRowMapper(), id);
    }

        // Repository (TransactionsRepository.java)
    public int insertTransaction(Long fromHospitalId, Long toHospitalId, Long requestId, int givenCount) {
        String sql = """
            INSERT INTO transactions (from_hospital_id, to_hospital_id, request_id, given_count) 
            VALUES (?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql, fromHospitalId, toHospitalId, requestId, givenCount);
    }
}
