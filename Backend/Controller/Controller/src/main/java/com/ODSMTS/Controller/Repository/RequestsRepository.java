package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.DTO.RequestDTO;
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

}
