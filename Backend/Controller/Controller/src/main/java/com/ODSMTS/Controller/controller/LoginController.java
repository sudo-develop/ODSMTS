
package com.ODSMTS.Controller.controller;

import com.ODSMTS.Controller.DTO.LoginRequest;
import com.ODSMTS.Controller.DTO.LoginResponse;
import com.ODSMTS.Controller.Entity.User;
import com.ODSMTS.Controller.Entity.Hospital;
import com.ODSMTS.Controller.Repository.UserRepository;
import com.ODSMTS.Controller.Repository.HospitalRepository;
import com.ODSMTS.Controller.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HospitalRepository hospitalRepository;  // ✅ Autowire HospitalRepository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            System.out.println("❌ User not found: " + request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        User user = optionalUser.get();

        System.out.println("✅ User found: " + user.getUsername());
        System.out.println("🔄 Matching Passwords: " + passwordEncoder.matches(request.getPassword(), user.getPasswordHash()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            System.out.println("❌ Password does not match!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // ✅ Fetch hospital details using hospitalId
        Hospital hospital = hospitalRepository.findById(user.getHospitalId());

        // ✅ Generate JWT token with username and roleId
        String token = jwtUtil.generateToken(user.getUsername(), user.getRoleId());


        // ✅ Return response with hospital details
        return ResponseEntity.ok(new LoginResponse(
                "Login successful",
                token,
                user.getUsername(),
                user.getEmail(),
                user.getRoleId(),
                user.getHospitalId(),
                hospital  // Include hospital details in response
        ));
    }
}    
