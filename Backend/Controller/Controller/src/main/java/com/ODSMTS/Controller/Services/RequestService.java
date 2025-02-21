package com.ODSMTS.Controller.Services;


// import com.ODSMTS.Controller.Entity.Request;
// import com.ODSMTS.Controller.Repository.DrugRepository;
// import com.ODSMTS.Controller.Repository.HospitalRepository;
// import com.ODSMTS.Controller.Repository.RequestRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.List;

// @Service
// public class RequestService {
//     private final RequestRepository requestRepository;
//     private final DrugRepository drugRepository;
//     private final HospitalRepository hospitalRepository;

//     @Autowired
//     public RequestService(RequestRepository requestRepository,
//                           DrugRepository drugRepository,
//                           HospitalRepository hospitalRepository) {
//         this.requestRepository = requestRepository;
//         this.drugRepository = drugRepository;
//         this.hospitalRepository = hospitalRepository;
//     }

//     @Transactional
//     public Request createRequest(Request request) {
//         // Validate foreign keys
//         if (!drugRepository.existsById(request.getDrugId())) {
//             throw new IllegalArgumentException("Invalid drug ID");
//         }
//         if (!hospitalRepository.existsById(request.getCreatedBy())) {
//             throw new IllegalArgumentException("Invalid hospital ID");
//         }
//         return requestRepository.save(request);
//     }

//     public List<Request> getRequestsByHospital(Long hospitalId) {
//         return requestRepository.findByCreatedBy(hospitalId);
//     }

//     @Transactional
//     public void updateRequestStatus(Long requestId, Request.RequestStatus status) {
//         requestRepository.updateStatus(requestId, status);
//     }
// }
