package com.ODSMTS.Controller.Entity;

public class OrphanDrug {
    private Long id;
    private String drugName;
    private String standardPackageUnit;
    private Integer quantityPerUnit;
    private String shelfLife;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getStandardPackageUnit() { return standardPackageUnit; }
    public void setStandardPackageUnit(String standardPackageUnit) { this.standardPackageUnit = standardPackageUnit; }
    public Integer getQuantityPerUnit() { return quantityPerUnit; }
    public void setQuantityPerUnit(Integer quantityPerUnit) { this.quantityPerUnit = quantityPerUnit; }
    public String getShelfLife() { return shelfLife; }
    public void setShelfLife(String shelfLife) { this.shelfLife = shelfLife; }
}