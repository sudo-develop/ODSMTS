import React, { useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import Sidebar from "../../sidebar/sidebar";
import Header from "../Header/header";
import "../../styles/inventoryForm-style.css";
import { addDrugInventory } from "../../../api";

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

const AddInventoryForm = () => {
  const hospitalId = useSelector((state) => state.user.hospitalId);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    drugId: "",
    drugFormId: "",
    expiryDate: "",
  });
const token = useSelector((state) => state.user.token);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
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
  }

  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <Header />
        <div className="form-container">
          <h2>Add Inventory</h2>
          <form onSubmit={handleSubmit}>
            <label>Drug Name:</label>
            <select name="drugId" value={formData.drugId} onChange={handleChange} required>
              <option value="">Select Drug</option>
              {drugOptions.map((drug) => (
                <option key={drug.id} value={drug.id}>{drug.name}</option>
              ))}
            </select>

            <label>Drug Form:</label>
            <select name="drugFormId" value={formData.drugFormId} onChange={handleChange} required>
              <option value="">Select Form</option>
              {drugFormOptions.map((form) => (
                <option key={form.id} value={form.id}>{form.name}</option>
              ))}
            </select>

            <label>Expiry Date:</label>
            <input type="date" name="expiryDate" value={formData.expiryDate} onChange={handleChange} required />
            
            <div className="button-group">
              <button type="submit" className="submit-btn">Add Inventory</button>
              <button type="button" className="back-btn" onClick={() => navigate("/hospital-inventory")}>Back</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddInventoryForm;
