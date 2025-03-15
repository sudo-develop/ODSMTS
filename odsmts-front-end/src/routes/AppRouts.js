import { BrowserRouter, Navigate } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import Login from "../components/pages/login";
import AdminDashboard from "../components/pages/AdminDashboard";
import HospitalDashboard from "../components/pages/HospitalDashboard";
import HospitalInventory from "../components/pages/HospitalInventory";
import HospitalReport from "../components/pages/Hospitalreport"
import PrivateRoute from "./PrivateRouts";
import AddInventoryForm from "../components/pages/create form/AddInventoryForm"

const AppRoutes = () => {
  const roleId = useSelector((state) => state.user.roleId);

  return (
    <Routes>
     {/* Redirect root ("/") to "/login" automatically */}
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
      <Route
        path="/hospital-inventory"
        element={
          <PrivateRoute>
            <HospitalInventory />
          </PrivateRoute>
        }
      />
       <Route path="/add-inventory" element={<AddInventoryForm />} />
       <Route
        path="/hospital-reports"
        element={
          <PrivateRoute>
            <HospitalReport />
          </PrivateRoute>
        }
      />
    </Routes>
  );
};

export default AppRoutes;
