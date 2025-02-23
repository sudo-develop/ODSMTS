package com.ODSMTS.Controller.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Inventory {
    private Long id;
    private Long hospitalId;
    private Long drugId;
    private Long drugFormId;
    private LocalDate expiryDate;
    private Boolean isExpired;
    private Boolean isConsumed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long currentHospitalId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHospitalId() { return hospitalId; }
    public void setHospitalId(Long hospitalId) { this.hospitalId = hospitalId; }
    public Long getDrugId() { return drugId; }
    public void setDrugId(Long drugId) { this.drugId = drugId; }
    public Long getDrugFormId() { return drugFormId; }
    public void setDrugFormId(Long drugFormId) { this.drugFormId = drugFormId; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public Boolean getExpired() { return isExpired; }
    public void setExpired(Boolean expired) { isExpired = expired; }
    public Boolean getConsumed() { return isConsumed; }
    public void setConsumed(Boolean consumed) { isConsumed = consumed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Long getCurrentHospitalId() { return currentHospitalId; }
    public void setCurrentHospitalId(Long currentHospitalId) { this.currentHospitalId = currentHospitalId; }
}