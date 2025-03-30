import React from "react";

const Dropdown = ({ label, options, selectedValue, setSelectedValue }) => {
  return (
    <div>
      <label>{label}:</label>
      <select value={selectedValue} onChange={(e) => setSelectedValue(e.target.value)} required>
        <option value="">Select {label}</option>
        {options.map((option) => (
          <option key={option.id} value={option.id}>
            {option.name}
          </option>
        ))}
      </select>
    </div>
  );
};

export default Dropdown;
