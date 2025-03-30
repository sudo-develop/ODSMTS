package com.ODSMTS.Controller.controller;

import com.ODSMTS.Controller.DTO.RequestDTO;
import com.ODSMTS.Controller.DTO.FulfillRequestDTO;
import com.ODSMTS.Controller.DTO.RequestCreateDTO;
import com.ODSMTS.Controller.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/requests")
public class RequestsController {
    
    @Autowired
    private RequestService requestService;

    @GetMapping("/all")
    public List<RequestDTO> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<RequestDTO> getRequestsByHospital(@PathVariable Long hospitalId) {
        return requestService.getRequestsByHospital(hospitalId);
    }

    @GetMapping("/request/{requestId}")
    public List<RequestDTO> getRequestByRequestId(@PathVariable Long requestId) {
        return requestService.getRequestByRequestId(requestId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRequest(@RequestBody RequestCreateDTO requestDTO) {
        String message = requestService.createRequest(requestDTO);
        return ResponseEntity.ok(message);
    }

    // Controller (RequestsController.java)
        @PostMapping("/fulfill")
        public ResponseEntity<Map<String, Object>> fulfillRequest(@RequestBody FulfillRequestDTO fulfillRequestDTO) {
            Map<String, Object> response = requestService.fulfillRequest(
                fulfillRequestDTO.getRequestId(),
                fulfillRequestDTO.getFromHospitalId(),
                fulfillRequestDTO.getToHospitalId(),
                fulfillRequestDTO.getFulfilledQuantity()
            );
            return ResponseEntity.ok(response);
        }

}
