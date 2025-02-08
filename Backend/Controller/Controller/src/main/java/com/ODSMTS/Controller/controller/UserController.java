package com.ODSMTS.Controller.controller;

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


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request, @RequestHeader("Authorization") String token) {
        // Extract username from the JWT token
        String loggedInUsername = jwtUtil.extractUsername(token.substring(7));

        // Fetch the user who is making the request
        User loggedInUser = userRepository.findByUsername(loggedInUsername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user"));

        // Check if the logged-in user has role_id = 2
        if (loggedInUser.getRoleId() != 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to create users.");
        }

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new user with role_id = 3
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPasswordHash(hashedPassword);
        newUser.setRoleId(3); // Assign role_id = 3

        userRepository.save(newUser);

        return ResponseEntity.ok("User created successfully with role_id 3");
    }
}
