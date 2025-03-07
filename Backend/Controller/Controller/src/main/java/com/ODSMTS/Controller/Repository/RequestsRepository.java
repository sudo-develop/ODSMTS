package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.DTO.RequestDTO;
import com.ODSMTS.Controller.Entity.Request;
import com.ODSMTS.Controller.DTO.RequestCreateDTO;
import com.ODSMTS.Controller.utils.RequestDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RequestsRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    public List<RequestDTO> getAllRequests() {
        String sql = """
            SELECT 
                hs.name AS hospital_name,
                od.drug_name,
                df.form_name,
                rq.quantity,
                rq.fulfilled_by,
                rq.fulfilled_quantity,
                rq.status,
                rq.request_date
            FROM requests rq
            LEFT JOIN orphan_drugs od ON rq.drug_id = od.id
            LEFT JOIN hospitals hs ON rq.created_by = hs.id
            LEFT JOIN drug_form df ON rq.drug_form_id = df.id
            ORDER BY rq.quantity DESC;
        """;

        return jdbcTemplate.query(sql, new RequestDetailsRowMapper());
    }

    public List<RequestDTO> getRequestsByHospital(Long hospitalId) {
        String sql = """
            SELECT 
                hs.name AS hospital_name,
                od.drug_name,
                df.form_name,
                rq.quantity,
                rq.fulfilled_by,
                rq.fulfilled_quantity,
                rq.status,
                rq.request_date
            FROM requests rq
            LEFT JOIN orphan_drugs od ON rq.drug_id = od.id
            LEFT JOIN hospitals hs ON rq.created_by = hs.id
            LEFT JOIN drug_form df ON rq.drug_form_id = df.id
            WHERE rq.created_by = ?
            ORDER BY rq.quantity DESC;
        """;
    
        return jdbcTemplate.query(sql, new RequestDetailsRowMapper(), hospitalId);
    }
    
    public int createRequest(RequestCreateDTO request) {
    String sql = """
        INSERT INTO requests (drug_id, drug_form_id, created_by, fulfilled_by, quantity, fulfilled_quantity, request_date, status)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

    return jdbcTemplate.update(
        sql,
        request.getDrugId(),
        request.getDrugFormId(),
        request.getCreatedBy(),
        request.getFulfilledBy(),
        request.getQuantity(),
        request.getFulfilledQuantity(),
        LocalDateTime.now(),  // Automatically setting request_date
        request.getStatus()
    );
}

// Repository (RequestsRepository.java)
public int updateRequest(Long requestId, Long fulfilledBy, int fulfilledQuantity) {
    String sql = """
        UPDATE requests 
        SET fulfilled_by = ?, fulfilled_quantity = ?, status = CASE 
            WHEN quantity = ? THEN 'FULFILLED' 
            ELSE 'PENDING' 
        END 
        WHERE id = ?
    """;
    return jdbcTemplate.update(sql, fulfilledBy, fulfilledQuantity, fulfilledQuantity, requestId);
}


public Request getRequestById(Long requestId) {
    String sql = """
        SELECT id, drug_id, drug_form_id, created_by, fulfilled_by, quantity, 
               fulfilled_quantity, request_date, status 
        FROM requests 
        WHERE id = ?
    """;

    return jdbcTemplate.queryForObject(sql, new Object[]{requestId}, (rs, rowNum) -> {
        Request request = new Request();
        request.setId(rs.getLong("id"));
        request.setDrugId(rs.getLong("drug_id"));
        request.setDrugFormId(rs.getLong("drug_form_id"));
        request.setCreatedBy(rs.getLong("created_by"));
        request.setFulfilledBy(rs.getLong("fulfilled_by"));
        request.setQuantity(rs.getInt("quantity"));
        request.setFulfilledQuantity(rs.getInt("fulfilled_quantity"));
        request.setRequestDate(rs.getTimestamp("request_date").toLocalDateTime());
        request.setStatus(Request.RequestStatus.valueOf(rs.getString("status")));
        return request;
    });
}

}
