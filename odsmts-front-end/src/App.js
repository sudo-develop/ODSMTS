import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./components/login.js";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/admin-dashboard" />
        <Route path="/user-dashboard" />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
