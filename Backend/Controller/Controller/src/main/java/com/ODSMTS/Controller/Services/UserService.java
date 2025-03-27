package com.ODSMTS.Controller.Services;

import com.ODSMTS.Controller.DTO.CreateUserRequest;
import com.ODSMTS.Controller.Entity.User;
import com.ODSMTS.Controller.Repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(CreateUserRequest request) {
        try {
            // Check if username exists
            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists. Please choose a different username.");
            }

            // Check if email exists
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists. Please use a different email.");
            }

            // Save new user
            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setPasswordHash(passwordEncoder.encode(request.getPassword()));
            newUser.setRoleId(request.getRoleId());
            newUser.setHospitalId(request.getHospitalId()); // ✅ Added hospitalId

            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error saving user. Please try again.");
        }
    }

     // ✅ Fetch multiple emails for a given hospital ID
     public List<String> getUserEmailsByHospital(Long hospitalId) {
        return userRepository.findEmailsByHospitalId(hospitalId);
    }
}
