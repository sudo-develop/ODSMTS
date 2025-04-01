import React, { useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Sidebar from "../../sidebar/sidebar";
import Header from "../Header/header";
import "../../styles/inventoryForm-style.css"; // Ensure CSS is updated
import { addDrugInventory } from "../../../api";
import { FaCaretDown, FaCalendarAlt } from "react-icons/fa"; // Import icons

const drugOptions = [
  { id: 1, name: "Keytruda" },
  { id: 2, name: "Opdivo" },
  { id: 3, name: "Darzalex" },
  { id: 4, name: "Soliris" },
  { id: 5, name: "Hemlibra" },
  { id: 6, name: "Revlimid" },
  { id: 7, name: "Imbruvica" },
  { id: 8, name: "Jakafi" },
  { id: 9, name: "Venclexta" },
  { id: 10, name: "Zolgensma" },
];

const drugFormOptions = [
  { id: 1, name: "Vial" },
  { id: 2, name: "Tablet" },
  { id: 3, name: "Capsule" },
];

const AddRequestForm = () => {
  const hospitalId = useSelector((state) => state.user.hospitalId);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    drugId: "",
    drugFormId: "",
    expiryDate: "",
  });
  const token = useSelector((state) => state.user.token);

  // List of drug IDs that should be restricted to "Tablet" only
  const tabletOnlyDrugs = [6, 7, 8, 9];

  // Determine available drug forms based on selected drug
  const filteredDrugForms = tabletOnlyDrugs.includes(parseInt(formData.drugId))
    ? drugFormOptions.filter((form) => form.id === 2) // Only allow "Tablet"
    : drugFormOptions;

  const handleChange = (e) => {
    const { name, value } = e.target;

    // If drug is changed, reset the drugFormId if it's invalid
    if (name === "drugId") {
      const newDrugId = parseInt(value);
      setFormData({
        ...formData,
        drugId: newDrugId,
        drugFormId: tabletOnlyDrugs.includes(newDrugId) ? 2 : "", // Auto-set Tablet or reset
      });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      hospitalId,
      currentHospitalId: hospitalId,
      drugId: parseInt(formData.drugId),
      drugFormId: parseInt(formData.drugFormId),
      expiryDate: formData.expiryDate,
      isExpired: false,
      isConsumed: false,
    };

    try {
      await addDrugInventory(payload, token);
      alert("Inventory added successfully!");
      navigate("/hospital-inventory");
    } catch (error) {
      console.error("Error adding inventory:", error);
      alert("Failed to add inventory");
    }
  };

  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <Header />
        <div className="form-container">
          <h2>Add Request</h2>
          <form onSubmit={handleSubmit}>
            {/* Drug Name Selection */}
            <div className="input-group">
              <label>Drug Name:</label>
              <div className="custom-select">
                <select name="drugId" value={formData.drugId} onChange={handleChange} required>
                  <option value="">Select Drug</option>
                  {drugOptions.map((drug) => (
                    <option key={drug.id} value={drug.id}>{drug.name}</option>
                  ))}
                </select>
                <FaCaretDown className="icon" />
              </div>
            </div>

            {/* Drug Form Selection (Filters dynamically) */}
            <div className="input-group">
              <label>Drug Form:</label>
              <div className="custom-select">
                <select name="drugFormId" value={formData.drugFormId} onChange={handleChange} required>
                  <option value="">Select Form</option>
                  {filteredDrugForms.map((form) => (
                    <option key={form.id} value={form.id}>{form.name}</option>
                  ))}
                </select>
                <FaCaretDown className="icon" />
              </div>
            </div>

            {/* Expiry Date Selection */}
            {/* <div className="input-group">
              <label>Expiry Date:</label>
              <div className="custom-input">
                <input type="date" name="expiryDate" value={formData.expiryDate} onChange={handleChange} required />
                <FaCalendarAlt className="icon" />
              </div>
            </div> */}

            {/* Buttons */}
            <div className="button-group">
              <button type="submit" className="submit-btn">Add Request</button>
              <button type="button" className="back-btn" onClick={() => navigate("/hospital-requests")}>Back</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddRequestForm;
