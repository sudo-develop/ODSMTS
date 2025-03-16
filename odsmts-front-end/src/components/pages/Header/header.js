import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { logout } from "../../../redux/userSlice"; // ✅ Import logout action
import "../../styles/header-style.css";

const Header = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const userState = useSelector((state) => state.user);
  const { hospitalDetails, username, email } = userState;

  if (!hospitalDetails) {
    return <p>Loading...</p>;
  }

  // ✅ Logout Handler
  const handleLogout = () => {
    dispatch(logout()); // Clear Redux state
    localStorage.removeItem("userToken"); // Clear token from localStorage
    navigate("/login"); // Redirect to login page
  };

  return (
    <header className="header-container">
      <div className="hospital-info">
        <h2>{hospitalDetails.name || "Hospital Name"}</h2>
        <p>
          {hospitalDetails.address}, {hospitalDetails.city}, {hospitalDetails.state} - {hospitalDetails.pincode}
        </p>
        <p>
          <strong>Contact:</strong> {hospitalDetails.contactNumber}
        </p>
      </div>

      <div className="user-section">
        <div className="user-info">
          <p><span className="highlight">{username}</span></p>
          <p>{email}</p>
        </div>
        {/* ✅ Logout Button */}
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </div>
    </header>
  );
};

export default Header;
