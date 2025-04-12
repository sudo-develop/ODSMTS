import { BrowserRouter, Navigate } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import Login from "../components/pages/login";
import AdminDashboard from "../components/pages/AdminDashboard";
import HospitalDashboard from "../components/pages/HospitalDashboard";
import HospitalInventory from "../components/pages/HospitalInventory";
import HospitalReport from "../components/pages/Hospitalreport";
import PrivateRoute from "./PrivateRouts";
import AddInventoryForm from "../components/pages/create form/AddInventoryForm";
import ConnectHospitalLayout from "../components/pages/ConnectHospitalLayout"; // Add this import
import HospitalRequests from "../components/pages/HospitalRequests";
import AddRequestForm from "../components/pages/create form/AddRequestForm";
import FulfillRequestLayout from "../components/pages/FulfillRequestLayout";
const AppRoutes = () => {
  const roleId = useSelector((state) => state.user.roleId);

  return (
    <Routes>
      <Route path="/" element={<Navigate to="/login" />} />
      <Route path="/login" element={<Login />} />
      
      <Route
        path="/admin-dashboard"
        element={
          <PrivateRoute>
            <AdminDashboard />
          </PrivateRoute>
        }
      />
      
      <Route
        path="/hospital-dashboard"
        element={
          <PrivateRoute>
            <HospitalDashboard />
          </PrivateRoute>
        }
      />
      
      {/* Add the new connect hospital route */}
      <Route
        path="/connect/hospital"
        element={
          <PrivateRoute>
            <ConnectHospitalLayout />
          </PrivateRoute>
        }
      />
      {/* Other protected routes */}
      <Route
        path="/fulfill/request/:requestId"
        element={
          <PrivateRoute>
            <FulfillRequestLayout />
          </PrivateRoute>
        }
      /> 
      <Route
        path="/hospital-inventory"
        element={
          <PrivateRoute>
            <HospitalInventory />
          </PrivateRoute>
        }
      />
      
      <Route
        path="/add-inventory"
        element={
          <PrivateRoute>
            <AddInventoryForm />
          </PrivateRoute>
        }
      />

      <Route
        path="/add-request"
        element={
          <PrivateRoute>
            <AddRequestForm />
          </PrivateRoute>
        }
      />
      
      <Route
        path="/hospital-reports"
        element={
          <PrivateRoute>
            <HospitalReport />
          </PrivateRoute>
        }
      />

      <Route
        path="/hospital-requests"
        element={
          <PrivateRoute>
            <HospitalRequests />
          </PrivateRoute>
        }
      />
    </Routes>
  );
};

export default AppRoutes;