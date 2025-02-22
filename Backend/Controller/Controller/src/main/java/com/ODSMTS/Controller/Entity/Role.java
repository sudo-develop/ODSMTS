package com.ODSMTS.Controller.Entity;


public class Role {
    private Long id;
    private String role;  // Maps to the "Roles" column in the database

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}