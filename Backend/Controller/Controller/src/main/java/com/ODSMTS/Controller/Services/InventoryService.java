package com.ODSMTS.Controller.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ODSMTS.Controller.DTO.InventoryDetailsDTO;
import com.ODSMTS.Controller.Repository.InventoryRepository;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryDetailsDTO> getInventoryDetailsByHospitalId(Long hospitalId) {
        return inventoryRepository.findInventoryDetailsByHospitalId(hospitalId);
    }
}