package com.ODSMTS.Controller.utils;

import com.ODSMTS.Controller.Entity.DrugForm;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugFormRowMapper implements RowMapper<DrugForm> {
    @Override
    public DrugForm mapRow(ResultSet rs, int rowNum) throws SQLException {
        DrugForm form = new DrugForm();
        form.setId(rs.getLong("id"));
        form.setFormName(rs.getString("form_name"));
        return form;
    }
}
