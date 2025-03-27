package com.ODSMTS.Controller.controller;
/* 
import com.ODSMTS.Controller.DTO.CreateUserRequest;
import com.ODSMTS.Controller.Entity.User;
import com.ODSMTS.Controller.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
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
*/

import com.ODSMTS.Controller.DTO.CreateUserRequest;
import com.ODSMTS.Controller.Entity.User;
import com.ODSMTS.Controller.Services.UserService;
import com.ODSMTS.Controller.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;  // Utility class to handle JWT operations

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(
            @RequestBody CreateUserRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Map<String, String> response = new HashMap<>();

        try {
            // Validate Authorization header
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.put("error", "Missing or invalid Authorization header");
                return ResponseEntity.status(401).body(response);
            }

            // Extract token and decode user role
            String token = authHeader.substring(7);
            int roleId = jwtUtil.extractRoleId(token);

            // Only allow users with roleId = 2 to create new users
            if (roleId != 2) {
                response.put("error", "Access denied. Only roleId = 2 can create a user.");
                return ResponseEntity.status(403).body(response);
            }

            // Proceed with user creation
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

    // ✅ Fetch multiple user emails for a given hospital ID
    @GetMapping("/emails/{hospitalId}")
    public ResponseEntity<List<String>> getUserEmailsByHospital(@PathVariable Long hospitalId) {
        List<String> emails = userService.getUserEmailsByHospital(hospitalId);
        
        // ✅ Ensure an empty list is returned instead of null
        return ResponseEntity.ok(emails != null ? emails : List.of());
    }

}
