// package com.ODSMTS.Controller.DTO;

// public class LoginResponse {
//     private String message;
//     private String token;
//     private String username;
//     private int roleId;

//     public LoginResponse(String message, String token, String username, int roleId) {
//         this.message = message;
//         this.token = token;
//         this.username = username;
//         this.roleId = roleId;
//     }

//     // Getters and Setters
//     public String getMessage() { return message; }
//     public void setMessage(String message) { this.message = message; }

//     public String getToken() { return token; }
//     public void setToken(String token) { this.token = token; }

//     public String getUsername() { return username; }
//     public void setUsername(String username) { this.username = username; }

//     public int getRoleId() { return roleId; }
//     public void setRoleId(int roleId) { this.roleId = roleId; }
// }

package com.ODSMTS.Controller.DTO;

import com.ODSMTS.Controller.Entity.Hospital;

public class LoginResponse {
    private String message;
    private String token;
    private String username;
    private String email;
    private Integer roleId;
    private Integer hospitalId;
    private Hospital hospitalDetails;  // ✅ Added hospital details

    public LoginResponse(String message, String token, String username, String email, Integer roleId, Integer hospitalId, Hospital hospitalDetails) {
        this.message = message;
        this.token = token;
        this.username = username;
        this.email = email;
        this.roleId = roleId;
        this.hospitalId = hospitalId;
        this.hospitalDetails = hospitalDetails;
    }

    // ✅ Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public Integer getHospitalId() { return hospitalId; }
    public void setHospitalId(Integer hospitalId) { this.hospitalId = hospitalId; }

    public Hospital getHospitalDetails() { return hospitalDetails; }
    public void setHospitalDetails(Hospital hospitalDetails) { this.hospitalDetails = hospitalDetails; }
}
