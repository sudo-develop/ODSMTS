import React, { useEffect, useState } from "react";
import { data } from "react-router-dom";
import "../styles/request-table.css";

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
      id: 1,
      name: "Abc Xyz",
      email: "example.com",
      phone: "1234567890",
        status: "Pending",
    },
    {
      id: 2,
      name: "pqr stu",
      email: "example.com",
      phone: "1234567890",
      status: "Approved",
    },
    {
      id: 3,
      name: "lamda",
      email: "example.com",
      phone: "1234567890",
      status: "Rejected",
    }];

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">ID</th>
            <th className="border p-2">Name</th>
            <th className="border p-2">Email</th>
            <th className="border p-2">Phone</th>
            <th className="border p-2">Status</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((item) => (
              <tr key={item.id} className="border">
                <td className="border p-2">{item.id}</td>
                <td className="border p-2">{item.name}</td>
                <td className="border p-2">{item.email}</td>
                <td className="border p-2">{item.phone}</td>
                <td className="border p-2">{item.status}</td>
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