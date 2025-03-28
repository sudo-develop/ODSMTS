package com.ODSMTS.Controller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ODSMTS.Controller.DTO.InventoryDetailsDTO;
import com.ODSMTS.Controller.Entity.Inventory;
import com.ODSMTS.Controller.Services.InventoryService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<InventoryDetailsDTO>> getInventoryByHospitalId(
        @PathVariable Long hospitalId
    ) {
        List<InventoryDetailsDTO> details = inventoryService.getInventoryDetailsByHospitalId(hospitalId);
        return ResponseEntity.ok(details);
    }

     @PostMapping("/addDrug")
    public ResponseEntity<Map<String, Object>> addInventory(@RequestBody Inventory inventory) {
        Integer inventoryId = inventoryService.saveInventory(inventory);
        
        System.out.println("Received Inventory Request: " + inventory);

        Map<String, Object> response = new HashMap<>();
        if (inventoryId != null) {
            response.put("message", "Drug added successfully");
            response.put("inventoryId", inventoryId);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Failed to add inventory");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/available-drug-count")
    public ResponseEntity<Map<String, Integer>> getAvailableDrugCount(
            @RequestParam Long drugId,
            @RequestParam Long drugFormId,
            @RequestParam Long hospitalId) {

        Integer count = inventoryService.getAvailableDrugCount(drugId, drugFormId, hospitalId);
        Map<String, Integer> response = new HashMap<>();
        response.put("availableCount", count);
        return ResponseEntity.ok(response);
    }
}