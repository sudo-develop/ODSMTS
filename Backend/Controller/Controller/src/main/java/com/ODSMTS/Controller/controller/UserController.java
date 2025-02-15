package com.ODSMTS.Controller.controller;
/* 
import com.ODSMTS.Controller.DTO.CreateUserRequest;
import com.ODSMTS.Controller.Entity.User;
import com.ODSMTS.Controller.Repository.UserRepository;
import com.ODSMTS.Controller.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        // Debugging: Print received request data
        System.out.println("Received username: " + request.getUsername());
        System.out.println("Received email: " + request.getEmail());
        System.out.println("Received roleId: " + request.getRoleId());
    
        String hashedPassword = passwordEncoder.encode(request.getPassword());
    
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail()); // Ensure email is being set
        newUser.setPasswordHash(hashedPassword);
        newUser.setRoleId(request.getRoleId());
    
        userRepository.save(newUser);
        
        //System.out.println("Saved user email: " + newUser.getEmail()); // Check saved email
    
        return ResponseEntity.ok("User created successfully");
    }
}
*/
import com.ODSMTS.Controller.DTO.CreateUserRequest;
import com.ODSMTS.Controller.Entity.User;
import com.ODSMTS.Controller.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody CreateUserRequest request) {
        Map<String, String> response = new HashMap<>();
        try {
            User newUser = userService.registerUser(request);

            response.put("message", "User created successfully");
            response.put("username", newUser.getUsername());
            response.put("email", newUser.getEmail());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("error", "An unexpected error occurred. Please try again.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
