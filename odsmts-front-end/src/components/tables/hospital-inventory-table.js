import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux"; 
import "../styles/table.css";
import { HospitalInventory } from "../../api";
import { useNavigate } from "react-router-dom";

const HospitalInventoryTable = () => {
  const [data, setData] = useState([]); 
  const token = useSelector((state) => state.user.token);
  const hospitalId = useSelector((state) => state.user.hospitalId); // Get hospital ID from Redux

  useEffect(() => {
    const fetchData = async () => {
      try {
        console.log("hospitalid",hospitalId);
        if (hospitalId) {  // Ensure hospitalId is available
          const result = await HospitalInventory(hospitalId, token);
          setData(result);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [hospitalId, token]);  // Fetch data when hospitalId or token changes

  const navigate = useNavigate();

  return (
    <div className="overflow-x-auto">
      <div className="createInventroy">
        <button className="createInventroybtn" onClick={() => navigate("/hospital-reports")}>
          Create
        </button>
      </div>
      <br/>
      <table className="min-w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">DRUG</th>
            <th className="border p-2">FORM</th>
            <th className="border p-2">QTY. PER UNIT</th>
            <th className="border p-2">COUNT</th>
            <th className="border p-2">EXPIRY DATE</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((item) => (
              <tr key={item.id} className="border">
                <td className="border p-2">{item.drugName}</td>
                <td className="border p-2">{item.formName}</td>
                <td className="border p-2">{item.quantityPerUnit}</td>
                <td className="border p-2">{item.count}</td>
                <td className="border p-2">{item.expiryDate}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center p-2">Loading data...</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default HospitalInventoryTable;
