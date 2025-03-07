package com.ODSMTS.Controller.Services;

import com.ODSMTS.Controller.DTO.RequestDTO;
import com.ODSMTS.Controller.DTO.RequestCreateDTO;
import com.ODSMTS.Controller.Repository.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    
    @Autowired
    private RequestsRepository requestsRepository;

    public List<RequestDTO> getAllRequests() {
        return requestsRepository.getAllRequests();
    }

    public List<RequestDTO> getRequestsByHospital(Long hospitalId) {
        return requestsRepository.getRequestsByHospital(hospitalId);
    }

    public String createRequest(RequestCreateDTO requestDTO) {
        int rowsAffected = requestsRepository.createRequest(requestDTO);
        return rowsAffected > 0 ? "Request created successfully!" : "Failed to create request.";
    }
    
}
