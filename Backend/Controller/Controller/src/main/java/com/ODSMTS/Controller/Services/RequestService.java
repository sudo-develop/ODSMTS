package com.ODSMTS.Controller.Services;

import com.ODSMTS.Controller.DTO.RequestDTO;
import com.ODSMTS.Controller.Entity.Request;
import com.ODSMTS.Controller.DTO.RequestCreateDTO;
import com.ODSMTS.Controller.Repository.RequestsRepository;
import com.ODSMTS.Controller.Repository.TransactionsRepository;
import com.ODSMTS.Controller.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {
    
    @Autowired
    private RequestsRepository requestsRepository;
    
    @Autowired
    private TransactionsRepository transactionRepository;
    
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<RequestDTO> getAllRequests() {
        return requestsRepository.getAllRequests();
    }

    public List<RequestDTO> getRequestsByHospital(Long hospitalId) {
        return requestsRepository.getRequestsByHospital(hospitalId);
    }

    public List<RequestDTO> getRequestByRequestId(Long requestId) {
        return requestsRepository.getRequestByRequestId(requestId);
    }

    public String createRequest(RequestCreateDTO requestDTO) {
        int rowsAffected = requestsRepository.createRequest(requestDTO);
        return rowsAffected > 0 ? "Request created successfully!" : "Failed to create request.";
    }

    @Transactional
public Map<String, Object> fulfillRequest(Long requestId, Long fromHospitalId, Long toHospitalId, int fulfilledQuantity) {
    Map<String, Object> response = new HashMap<>();

    try {
        System.out.println("==== Fulfillment Request Received ====");
        System.out.println("Request ID: " + requestId);
        System.out.println("From Hospital ID: " + fromHospitalId);
        System.out.println("To Hospital ID: " + toHospitalId);
        System.out.println("Fulfilled Quantity Requested: " + fulfilledQuantity);

        // Fetch request details
        Request request = requestsRepository.getRequestById(requestId);
        if (request == null) {
            System.out.println("Request not found.");
            return Map.of("message", "Request not found.");
        }

        int currentFulfilledQuantity = request.getFulfilledQuantity();
        int totalRequested = request.getQuantity();

        System.out.println("Current Fulfilled Quantity: " + currentFulfilledQuantity);
        System.out.println("Total Requested Quantity: " + totalRequested);

        // Validate fulfillment constraints
        if (currentFulfilledQuantity >= totalRequested) {
            System.out.println("Request already fulfilled.");
            return Map.of("message", "Request is already fulfilled.");
        }
        if (fulfilledQuantity > (totalRequested - currentFulfilledQuantity)) {
            System.out.println("Fulfilled quantity exceeds remaining request quantity.");
            return Map.of("message", "Fulfilled quantity exceeds requested amount.");
        }

        // Check inventory before proceeding
        int availableInventory = inventoryRepository.getAvailableDrugCount(
            request.getDrugId(),
            request.getDrugFormId(),
            fromHospitalId
        );

        System.out.println("Available Inventory: " + availableInventory);

        if (availableInventory < fulfilledQuantity) {
            System.out.println("Inventory not sufficient. Cannot fulfill.");
            return Map.of("message", "Not enough inventory available for fulfillment.");
        }

        // Calculate new fulfilled quantity
        int newFulfilledQuantity = currentFulfilledQuantity + fulfilledQuantity;
        System.out.println("New Fulfilled Quantity after this request: " + newFulfilledQuantity);

        // Update request status
        requestsRepository.updateRequest(requestId, fromHospitalId, newFulfilledQuantity);
        System.out.println("Request fulfillment status updated.");

        // Insert transaction
        transactionRepository.insertTransaction(fromHospitalId, toHospitalId, requestId, fulfilledQuantity);
        System.out.println("Transaction inserted.");

        // Update inventory
        inventoryRepository.updateInventory(
            request.getDrugId(),
            request.getDrugFormId(),
            fromHospitalId,
            toHospitalId,
            fulfilledQuantity
        );
        System.out.println("Inventory updated.");

        response.put("status", "success");
        response.put("message", "Request fulfilled successfully.");
        response.put("from_hospital_id", fromHospitalId);
        response.put("to_hospital_id", toHospitalId);
        response.put("given_count", fulfilledQuantity);
        response.put("updated_request_id", requestId);

        System.out.println("==== Fulfillment Completed Successfully ====");

    } catch (DataAccessException e) {
        System.out.println("Database Error: " + e.getMessage());
        throw new RuntimeException("Database error: " + e.getMessage(), e);
    }

    return response;
}

    
}
