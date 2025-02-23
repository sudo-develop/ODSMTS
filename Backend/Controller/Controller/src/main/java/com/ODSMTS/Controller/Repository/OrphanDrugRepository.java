package com.ODSMTS.Controller.Repository;

import com.ODSMTS.Controller.Entity.OrphanDrug;
import com.ODSMTS.Controller.utils.OrphanDrugRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrphanDrugRepository {
    private final JdbcTemplate jdbcTemplate;
    private final OrphanDrugRowMapper rowMapper = new OrphanDrugRowMapper();

    public OrphanDrugRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(OrphanDrug drug) {
        String sql = "INSERT INTO orphan_drugs (drug_name, standard_package_unit, quantity_per_unit, shelf_life) " +
                     "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            drug.getDrugName(),
            drug.getStandardPackageUnit(),
            drug.getQuantityPerUnit(),
            drug.getShelfLife()
        );
    }

    public OrphanDrug findById(Long id) {
        String sql = "SELECT * FROM orphan_drugs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<OrphanDrug> findAll() {
        String sql = "SELECT * FROM orphan_drugs";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void update(OrphanDrug drug) {
        String sql = "UPDATE orphan_drugs SET drug_name = ?, standard_package_unit = ?, " +
                     "quantity_per_unit = ?, shelf_life = ? WHERE id = ?";
        jdbcTemplate.update(
            sql,
            drug.getDrugName(),
            drug.getStandardPackageUnit(),
            drug.getQuantityPerUnit(),
            drug.getShelfLife(),
            drug.getId()
        );
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM orphan_drugs WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}