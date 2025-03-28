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
      hospital: "/inventory/hospital/{id}",
      addDrug: "/inventory/addDrug",
      availableDrugCount: "/inventory/available-drug-count",
    },
    requests: {
      all: "/requests/all",
      byRequestId: "/requests/request/{requestId}",  // New API for fetching request by ID
      byHospitalId: "/requests/hospital/{hospitalId}", // New API for fetching requests by hospital ID
      fulfillRequest: "/requests/fulfill",
    },
    users: {
      emails: "/users/emails/{hospitalId}", // ✅ New API for fetching emails
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

export const addDrugInventory = async (payload, token) => {
  try {
    const response = await apiClient.post(apis.auth.inventory.addDrug, payload, {
      headers: { Authorization: `Bearer ${token}` },
    });

    console.log("Inventory Added:", response.data);
    return response.data;
  } catch (error) {
    console.error("Add Inventory Error:", error.response?.data || error);
    throw error.response?.data?.message || "Failed to add inventory";
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

// ✅ Fetch request by request ID (handles array response)

// ✅ Fetch request by request ID (handles array response)
export const fetchRequestById = async (requestId, token) => {
  try {
    const url = apis.auth.requests.byRequestId.replace("{requestId}", requestId);
    const response = await apiClient.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });

    // Validate response format
    if (!Array.isArray(response.data) || response.data.length === 0) {
      throw new Error('Invalid response format - expected non-empty array');
    }

    const requestData = response.data[0];
    console.log("Processed request data:", requestData);
    
    return requestData; // Return first item directly

  } catch (error) {
    console.error("Request fetch error:", {
      status: error.response?.status,
      data: error.response?.data,
      message: error.message
    });
    
    throw new Error(error.response?.data?.message || "Failed to fetch request details");
  }
};

// ✅ Fetch requests by hospital ID
export const fetchRequestsByHospital = async (hospitalId, token) => {
  try {
    const url = apis.auth.requests.byHospitalId.replace("{hospitalId}", hospitalId);
    const response = await apiClient.get(url, {
      headers: { Authorization: `Bearer ${token}` },
    });

    console.log("Requests by Hospital:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching requests by hospital:", error.response?.data || error);
    throw error.response?.data?.message || "Failed to fetch requests by hospital";
  }
};

// ✅ Function to fetch emails by hospital ID
export const fetchUserEmailsByHospital = async (hospitalId, token) => {
  try {
    // Validate input parameters
    if (!hospitalId || !token) {
      throw new Error("Missing required parameters");
    }

    // Safely construct endpoint URL
    const endpoint = apis.auth.users.emails
      .replace(/{hospitalId}/gi, encodeURIComponent(hospitalId));

    const response = await apiClient.get(endpoint, {
      headers: { 
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (process.env.NODE_ENV === "development") {
      console.debug("[API] Emails response:", response.data);
    }

    return response.data;
  } catch (error) {
    const errorMessage = error.response?.data?.message ||
      error.message ||
      "Failed to fetch hospital emails";

    console.error(`[API Error] fetchUserEmails: ${errorMessage}`, {
      hospitalId,
      status: error.response?.status,
      data: error.response?.data
    });

    throw new Error(errorMessage);
  }
};

export const getAvailableDrugCount = async (drugId, drugFormId, hospitalId, token) => {
  try {
    const response = await apiClient.get(apis.auth.inventory.availableDrugCount, {
      params: {
        drugId,
        drugFormId,
        hospitalId
      },
      headers: {
        Authorization: `Bearer ${token}`
      }
    });

    console.log("Available Drug Count Response:", response.data);

    return response.data;
  } catch (error) {
    console.error("Error fetching available drug count:", error.response?.data || error);
    throw error.response?.data?.message || "Failed to fetch available drug count";
  }
};


// ✅ Fulfill a request
export const fulfillRequest = async (payload, token) => {
  try {
    // Make sure fulfilledQuantity is a number
    const formattedPayload = {
      requestId: payload.requestId,
      fromHospitalId: payload.fromHospitalId,
      toHospitalId: payload.toHospitalId,
      fulfilledQuantity: Number(payload.fulfilledQuantity),
    };

    const response = await apiClient.post(apis.auth.requests.fulfillRequest, formattedPayload, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    console.log("Fulfill Request Response:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error fulfilling request:", error.response?.data || error);
    throw error.response?.data?.message || "Failed to fulfill request";
  }
};
