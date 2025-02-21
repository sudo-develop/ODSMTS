package com.ODSMTS.Controller.Entity;

import java.time.LocalDateTime;

public class Request {
    private Long id;
    private Long drugId;
    private Long drugFormId;
    private Long createdBy;
    private Long fulfilledBy;
    private int quantity;
    private int fulfilledQuantity;
    private LocalDateTime requestDate;
    private RequestStatus status;

    public enum RequestStatus {
        PENDING, APPROVED, REJECTED, FULFILLED
    }

    // Constructors
    public Request() {}

    public Request(Long drugId, Long drugFormId, Long createdBy, int quantity) {
        this.drugId = drugId;
        this.drugFormId = drugFormId;
        this.createdBy = createdBy;
        this.quantity = quantity;
    }

    // Getters & Setters (generated or manual)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDrugId() { return drugId; }
    public void setDrugId(Long drugId) { this.drugId = drugId; }
    public Long getDrugFormId() { return drugFormId; }
    public void setDrugFormId(Long drugFormId) { this.drugFormId = drugFormId; }
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    public Long getFulfilledBy() { return fulfilledBy; }
    public void setFulfilledBy(Long fulfilledBy) { this.fulfilledBy = fulfilledBy; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getFulfilledQuantity() { return fulfilledQuantity; }
    public void setFulfilledQuantity(int fulfilledQuantity) { this.fulfilledQuantity = fulfilledQuantity; }
    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
}