import React, { useEffect, useState } from "react";
import { data } from "react-router-dom";
import "../styles/table.css";

const RequestTable = () => {
//   const [data, setData] = useState([]);


  useEffect(() => {
    // Replace with your API URL
    // fetch("https://api.example.com/data")
    //   .then((response) => response.json())
    //   .then((data) => setData(data))
    //   .catch((error) => console.error("Error fetching data:", error));
  }, []);

  const data = [
    {
      date: 1,
      hospital_name: "Abc Xyz",
      drug_name: "example.com",
      quantity: "1234567890",
      status: "Pending",
      action: "Edit",
    },
    {
      date: 1,
      hospital_name: "Abc Xyz",
      drug_name: "example.com",
      quantity: "1234567890",
      status: "Pending",
      action: "Edit",
    },
    {
      date: 1,
      hospital_name: "Abc Xyz",
      drug_name: "example.com",
      quantity: "1234567890",
      status: "Pending",
      action: "Edit",
    }];

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">DATE</th>
            <th className="border p-2">HOSPITAL NAME</th>
            <th className="border p-2">DRUG NAME</th>
            <th className="border p-2">QTY</th>
            <th className="border p-2">STATUS</th>
            <th className="border p-2">ACTION</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((item) => (
              <tr key={item.id} className="border">
                <td className="border p-2">{item.date}</td>
                <td className="border p-2">{item.hospital_name}</td>
                <td className="border p-2">{item.drug_name}</td>
                <td className="border p-2">{item.quantity}</td>
                <td className="border p-2">{item.status}</td>
                <td className="border p-2">{item.action}</td>
              </tr>
            ))
           ) : (
            <tr>
              <td colSpan="5" className="text-center p-2">
                Loading data...
              </td>
            </tr>
          )} 
        </tbody>
      </table>
    </div>
  );
};

export default RequestTable;