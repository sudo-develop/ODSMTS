package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.Request;
import com.ODSMTS.Controller.utils.RequestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RequestRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert requestInserter;

    @Autowired
    public RequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.requestInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("requests")
                .usingGeneratedKeyColumns("id");
    }

    public Request save(Request request) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("drug_id", request.getDrugId());
        parameters.put("drug_form_id", request.getDrugFormId());
        parameters.put("created_by", request.getCreatedBy());
        parameters.put("fulfilled_by", request.getFulfilledBy());
        parameters.put("quantity", request.getQuantity());
        parameters.put("fulfilled_quantity", request.getFulfilledQuantity());
        parameters.put("request_date", request.getRequestDate());
        parameters.put("status", request.getStatus().name());

        Number generatedId = requestInserter.executeAndReturnKey(parameters);
        request.setId(generatedId.longValue());
        return request;
    }

    public List<Request> findByCreatedBy(Long hospitalId) {
        String sql = "SELECT * FROM requests WHERE created_by = ?";
        return jdbcTemplate.query(sql, new RequestRowMapper(), hospitalId);
    }

    public void updateStatus(Long requestId, Request.RequestStatus status) {
        String sql = "UPDATE requests SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.name(), requestId);
    }
}