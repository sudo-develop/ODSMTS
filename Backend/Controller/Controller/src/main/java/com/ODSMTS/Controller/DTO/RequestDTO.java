package com.ODSMTS.Controller.DTO;

import java.time.LocalDateTime;

public class RequestDTO {
    private String hospitalName;
    private String drugName;
    private String formName;
    private int quantity;
    private Long fulfilledBy;
    private int fulfilledQuantity;
    private String status;
    private LocalDateTime requestDate;

    // Getters and Setters
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }

    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Long getFulfilledBy() { return fulfilledBy; }
    public void setFulfilledBy(Long fulfilledBy) { this.fulfilledBy = fulfilledBy; }

    public int getFulfilledQuantity() { return fulfilledQuantity; }
    public void setFulfilledQuantity(int fulfilledQuantity) { this.fulfilledQuantity = fulfilledQuantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }
}
