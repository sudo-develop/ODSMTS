package com.ODSMTS.Controller.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ODSMTS.Controller.DTO.InventoryDetailsDTO;
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
}