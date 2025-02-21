/*import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import Login from "../components/Login";
import AdminDashboard from "../layouts/AdminDashboard";
import HospitalDashboard from "../layouts/HospitalDashboard";
import PrivateRoute from "./PrivateRoute";

const AppRoutes = () => {
  const roleId = useSelector((state) => state.user.roleId);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              {roleId === 2 ? <AdminDashboard /> : <HospitalDashboard />}
            </PrivateRoute>
          }
        />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
*/

import { BrowserRouter, Navigate } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import Login from "../components/pages/login";
import AdminDashboard from "../components/pages/AdminDashboard";
import HospitalDashboard from "../components/pages/HospitalDashboard";
import PrivateRoute from "./PrivateRouts";

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
    </Routes>
  );
};

export default AppRoutes;
