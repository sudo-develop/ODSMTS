package com.ODSMTS.Controller.DTO;

import java.time.LocalDate;

public class InventoryDetailsDTO {
    private String hospitalName;
    private String drugName;
    private String formName;
    private Integer quantityPerUnit;
    private LocalDate expiryDate;
    private Integer count; // Added field for COUNT(*)

    // Getters and Setters
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }
    public Integer getQuantityPerUnit() { return quantityPerUnit; }
    public void setQuantityPerUnit(Integer quantityPerUnit) { this.quantityPerUnit = quantityPerUnit; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
}