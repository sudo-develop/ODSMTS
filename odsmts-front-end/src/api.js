import axios from "axios";
//import { useSelector } from "react-redux";

const baseURL = "http://localhost:8080/api"

//const baseURL = "https://a387-2409-4081-2d07-957d-a06a-2319-e124-31db.ngrok-free.app/api"



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
    },
    requests: {
      all: "/requests/all",
    },
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

// ✅ Function to fetch all requests
export const fetchAllRequests = async (token) => {
  try {
    console.log("Fetching requests...");  // Debugging log

    const response = await apiClient.get(apis.auth.requests.all, {
      headers: { Authorization: `Bearer ${token}` },
    });

    console.log("API Response:", response.data); // Debugging log
    return response.data;
  } catch (error) {
    console.error("Error fetching requests:", error.response?.data || error);
    throw error.response?.data?.message || "Failed to fetch requests data";
  }
};


export { apiClient, baseURL };
