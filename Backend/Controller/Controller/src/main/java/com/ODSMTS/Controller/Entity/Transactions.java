package com.ODSMTS.Controller.Entity;

import java.time.LocalDateTime;

public class Transactions {
    private Long id;
    private Long fromHospitalId;
    private Long toHospitalId;
    private Long requestId;
    private Integer givenCount;
    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFromHospitalId() { return fromHospitalId; }
    public void setFromHospitalId(Long fromHospitalId) { this.fromHospitalId = fromHospitalId; }

    public Long getToHospitalId() { return toHospitalId; }
    public void setToHospitalId(Long toHospitalId) { this.toHospitalId = toHospitalId; }

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public Integer getGivenCount() { return givenCount; }
    public void setGivenCount(Integer givenCount) { this.givenCount = givenCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
