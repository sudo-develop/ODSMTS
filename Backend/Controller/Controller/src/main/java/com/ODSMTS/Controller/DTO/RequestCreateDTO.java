package com.ODSMTS.Controller.DTO;

import java.time.LocalDateTime;

public class RequestCreateDTO {
    private Long drugId;
    private Long drugFormId;
    private Long createdBy;
    private Long fulfilledBy;
    private Integer quantity;
    private Integer fulfilledQuantity;
    private LocalDateTime requestDate;
    private String status; // Enum: 'PENDING', 'APPROVED', etc.

    // Getters and Setters
    public Long getDrugId() { return drugId; }
    public void setDrugId(Long drugId) { this.drugId = drugId; }

    public Long getDrugFormId() { return drugFormId; }
    public void setDrugFormId(Long drugFormId) { this.drugFormId = drugFormId; }

    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }

    public Long getFulfilledBy() { return fulfilledBy; }
    public void setFulfilledBy(Long fulfilledBy) { this.fulfilledBy = fulfilledBy; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getFulfilledQuantity() { return fulfilledQuantity; }
    public void setFulfilledQuantity(Integer fulfilledQuantity) { this.fulfilledQuantity = fulfilledQuantity; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
