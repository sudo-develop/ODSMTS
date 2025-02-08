package com.ODSMTS.Controller.DTO;

public class LoginResponse {
    private String message;
    private String token;
    private String username;
    private int roleId;

    public LoginResponse(String message, String token, String username, int roleId) {
        this.message = message;
        this.token = token;
        this.username = username;
        this.roleId = roleId;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
}
