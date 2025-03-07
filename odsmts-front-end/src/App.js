// import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
// import Login from "./components/pages/login.js";

// function App() {
//   return (
//     <BrowserRouter>
//       <Routes>
//         <Route path="/" element={<Login />} />
//         <Route path="/Admin-dashboard" />
//         <Route path="/Hospital-dashboard" />
//         <Route path="*" element={<Navigate to="/" />} />
//       </Routes>
//     </BrowserRouter>
//   );
// }

// export default App;


import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/pages/login.js";
import AdminDashboard from "./components/pages/AdminDashboard"; // ✅ Import Admin Dashboard
import HospitalDashboard from "./components/pages/HospitalDashboard"; // ✅ Import Hospital Dashboard

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/Admin-dashboard" element={<AdminDashboard />} />
      <Route path="/Hospital-dashboard" element={<HospitalDashboard />} />
      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
  );
}

export default App;

