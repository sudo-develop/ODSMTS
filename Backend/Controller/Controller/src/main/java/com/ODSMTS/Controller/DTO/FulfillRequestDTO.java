package com.ODSMTS.Controller.DTO;

public class FulfillRequestDTO {
    private Long requestId;
    private Long fromHospitalId;
    private Long toHospitalId;
    private int fulfilledQuantity;

    // Getters and Setters
    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public Long getFromHospitalId() { return fromHospitalId; }
    public void setFromHospitalId(Long fromHospitalId) { this.fromHospitalId = fromHospitalId; }

    public Long getToHospitalId() { return toHospitalId; }
    public void setToHospitalId(Long toHospitalId) { this.toHospitalId = toHospitalId; }

    public int getFulfilledQuantity() { return fulfilledQuantity; }
    public void setFulfilledQuantity(int fulfilledQuantity) { this.fulfilledQuantity = fulfilledQuantity; }
}
