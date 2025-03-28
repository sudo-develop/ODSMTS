package com.ODSMTS.Controller.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ODSMTS.Controller.DTO.InventoryDetailsDTO;
import com.ODSMTS.Controller.Entity.Inventory;
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

    public Integer saveInventory(Inventory inventory) {
        if (inventory.getExpired() == null) {
            inventory.setExpired(false);
        }
        if (inventory.getConsumed() == null) {
            inventory.setConsumed(false);
        }
        return inventoryRepository.saveDrug(inventory);
    }

    public Integer getAvailableDrugCount(Long drugId, Long drugFormId, Long hospitalId) {
        if (drugId == null || drugFormId == null || hospitalId == null) {
            throw new IllegalArgumentException("All parameters are required");
        }
        return inventoryRepository.getAvailableDrugCount(drugId, drugFormId, hospitalId);
    }
}