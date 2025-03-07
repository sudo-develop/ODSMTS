package com.ODSMTS.Controller.controller;

import com.ODSMTS.Controller.DTO.RequestDTO;
import com.ODSMTS.Controller.DTO.RequestCreateDTO;
import com.ODSMTS.Controller.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/create")
    public ResponseEntity<String> createRequest(@RequestBody RequestCreateDTO requestDTO) {
        String message = requestService.createRequest(requestDTO);
        return ResponseEntity.ok(message);
    }

}
