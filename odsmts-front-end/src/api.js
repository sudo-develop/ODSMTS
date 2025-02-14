import axios from "axios";

const baseURL = "http://localhost:8080/api"

// Create Axios instance
const apiClient = axios.create({
  baseURL,
  headers: {
    "Content-Type": "application/json",
  },
});

// API Endpoints
export const apis = {
  auth: {
    login: "/login",
   
  },
};

// Function to call login API
export const loginUser = async (username, password) => {
    try {
      const response = await apiClient.post(apis.auth.login, { username, password });
  
      // Log response for debugging
      console.log("API Response:", response.data);
  
      return response.data; // Ensure this returns the correct response
    } catch (error) {
      console.error("Login Error:", error.response?.data || error);
      throw error.response?.data?.message || "Login failed";
    }
  };
  

// Export API client and endpoints
export { apiClient, baseURL };
