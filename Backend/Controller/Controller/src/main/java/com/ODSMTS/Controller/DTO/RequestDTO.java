package com.ODSMTS.Controller.DTO;

import java.time.LocalDateTime;

public class RequestDTO {
    private Long id;
    private String hospitalName;
    private String drugName;
    private String formName;
    private int quantity;
    private Long fulfilledBy; 
    private Long createdBy; 
    private String fulfilledByName; // ✅ Added Name
    private int fulfilledQuantity;
    private String status;
    private LocalDateTime requestDate;

    // Getters and Setters
    public Long getRequestId() { return id; }
    public void setRequestId(Long id) { this.id = id; }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }

    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Long getFulfilledBy() { return fulfilledBy; } // ✅ Getter for ID
    public void setFulfilledBy(Long fulfilledBy) { this.fulfilledBy = fulfilledBy; } // ✅ Setter for ID

    public Long getcreatedBy() { return createdBy; } // ✅ Getter for ID
    public void setcreatedBy(Long createdBy) { this.createdBy = createdBy; } // ✅ Setter for ID

    public String getFulfilledByName() { return fulfilledByName; } // ✅ Getter for Name
    public void setFulfilledByName(String fulfilledByName) { this.fulfilledByName = fulfilledByName; } // ✅ Setter for Name

    public int getFulfilledQuantity() { return fulfilledQuantity; }
    public void setFulfilledQuantity(int fulfilledQuantity) { this.fulfilledQuantity = fulfilledQuantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }
}
