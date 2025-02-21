package com.ODSMTS.Controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {
    
    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roleId")
    private int roleId;

    @JsonProperty("hospitalId")
    private int hospitalId;  // Fix variable naming (lowercase 'h')

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public int getHospitalId() { return hospitalId; }  // Fix getter to return hospitalId
    public void setHospitalId(int hospitalId) { this.hospitalId = hospitalId; }  // Fix setter
}
