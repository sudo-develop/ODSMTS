import axios from "axios";
import { useSelector } from "react-redux";

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
    createUser: "/create",
    inventory: {
      hospital:"/inventory/hospital/{id}",
      admin: "/inventory/admin/{id}",
    }
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

export const HospitalInventory = async (id, token) => {
  try {
    const response = await apiClient.get(apis.auth.inventory.hospital.replace("{id}", id), 
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    console.log("API Response:", response.data);

    return response.data;
  } catch (error) {
    console.error("Error:", error.response?.data || error);
    throw error.response?.data?.message || "Failed to fetch inventory data";
  }
};
  
export { apiClient, baseURL };
