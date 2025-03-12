import React from "react";
import { useSelector } from "react-redux";
import "../../styles/header-style.css"; // ✅ Add styles

const Header = () => {
  // Get user and hospital details from Redux
  const userState = useSelector((state) => state.user);

  // Debugging: Log the entire Redux state
  //console.log("Redux State in Header:", userState);

  const { hospitalDetails, username, email } = userState;

  if (!hospitalDetails) {
    return <p>Loading...</p>; // ✅ Show a fallback if data is not available yet
  }

  return (
    <header className="header-container">
      <div className="hospital-info">
        <h2>{hospitalDetails.name || "Hospital Name"}</h2>
        <p>{hospitalDetails.address}, {hospitalDetails.city}, {hospitalDetails.state} - {hospitalDetails.pincode}</p>
        <p><strong>Contact:</strong> {hospitalDetails.contactNumber}</p>
      </div>

      <div className="user-info">
        <p><span className="highlight">{username}</span></p>
        <p>{email}</p>
      </div>


    </header>
  );
};

export default Header;
