
// import React, { useEffect, useState } from "react";
// import { useSelector } from "react-redux";
// import { useNavigate } from "react-router-dom";
// import "../styles/table.css";
// import { fetchAllRequests } from "../../api";

// const RequestTable = () => {
//   const [data, setData] = useState([]);
//   const token = useSelector((state) => state.user.token);
//   const navigate = useNavigate();

//   useEffect(() => {
//     const getData = async () => {
//       try {
//         const result = await fetchAllRequests(token);
//         setData(result);
//       } catch (error) {
//         console.error("Error fetching data:", error);
//       }
//     };
//     token && getData();
//   }, [token]);

//   const handleConnect = (request) => {
//     navigate(`/connect/hospital`, {
//       state: {
//         requestId: request.requestId,
//         createdBy: request.createdBy, // Add this
//         hospitalName: request.hospitalName,
//         drugName: request.drugName,
//         quantity: request.quantity
//       }
//     });
//   };

//   return (
//     <div className="overflow-x-auto">
//       <table className="min-w-full border-collapse border border-gray-300">
//         <thead>
//           <tr className="bg-gray-200">
//             <th className="border p-2">HOSPITAL NAME</th>
//             <th className="border p-2">DRUG</th>
//             <th className="border p-2">FORM</th>
//             <th className="border p-2">QTY</th>
//             <th className="border p-2">FULFILLED BY</th>
//             <th className="border p-2">FULFILLED QTY</th>
//             <th className="border p-2">STATUS</th>
//             <th className="border p-2">REQUEST DATE</th>
//             <th className="border p-2">ACTION</th>
//           </tr>
//         </thead>
//         <tbody>
//           {data.length > 0 ? (
//             data.map((item) => (
//               <tr key={item.requestId} className="border">
//                 <td className="border p-2">{item.hospitalName}</td>
//                 <td className="border p-2">{item.drugName}</td>
//                 <td className="border p-2">{item.formName}</td>
//                 <td className="border p-2">{item.quantity}</td>
//                 <td className="border p-2">{item.fulfilledByName || "N/A"}</td>
//                 <td className="border p-2">{item.fulfilledQuantity}</td>
//                 <td className="border p-2">{item.status}</td>
//                 <td className="border p-2">
//                   {new Date(item.requestDate).toLocaleDateString()}
//                 </td>
//                 <td className="connect-button">
//                   <button
//                     onClick={() => handleConnect(item)}
//                     className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 disabled:bg-gray-400"
//                     disabled={item.status !== "APPROVED" && item.status !== "PENDING"}
//                   >
//                     Connect
//                   </button>
//                 </td>
//               </tr>
//             ))
//           ) : (
//             <tr>
//               <td colSpan="9" className="text-center p-2">
//                 Loading data...
//               </td>
//             </tr>
//           )}
//         </tbody>
//       </table>
//     </div>
//   );
// };

// export default RequestTable;


import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import "../styles/table.css";
import { fetchAllRequests } from "../../api";

const RequestTable = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true); // State for loading
  const token = useSelector((state) => state.user.token);
  const hospitalId = useSelector((state) => state.user.hospitalId);
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      try {
        setLoading(true); // Start loading
        const result = await fetchAllRequests(token);
        setData(result);
      } catch (error) {
        console.error("Error fetching data:", error);
      } finally {
        setLoading(false); // Stop loading
      }
    };
    token && getData();
  }, [token]);

  const handleConnect = (request) => {
    navigate(`/connect/hospital`, {
      state: {
        requestId: request.requestId,
        createdBy: request.createdBy,
        hospitalName: request.hospitalName,
        drugName: request.drugName,
        quantity: request.quantity
      }
    });
  };

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">HOSPITAL NAME</th>
            <th className="border p-2">DRUG</th>
            <th className="border p-2">FORM</th>
            <th className="border p-2">QTY</th>
            <th className="border p-2">FULFILLED BY</th>
            <th className="border p-2">FULFILLED QTY</th>
            <th className="border p-2">STATUS</th>
            <th className="border p-2">REQUEST DATE</th>
            <th className="border p-2">ACTION</th>
          </tr>
        </thead>
        <tbody>
          {loading ? (
            <tr>
              <td colSpan="9" className="text-center p-4">
                <div className="flex justify-center">
                  <div className="w-8 h-8 border-4 border-blue-500 border-solid border-t-transparent rounded-full animate-spin"></div>
                </div>
              </td>
            </tr>
          ) : data.length > 0 ? (
            data.map((item) => (
              <tr key={item.requestId} className="border">
                <td className="border p-2">{item.hospitalName}</td>
                <td className="border p-2">{item.drugName}</td>
                <td className="border p-2">{item.formName}</td>
                <td className="border p-2">{item.quantity}</td>
                <td className="border p-2">{item.fulfilledByName || "N/A"}</td>
                <td className="border p-2">{item.fulfilledQuantity}</td>
                <td className="border p-2">{item.status}</td>
                <td className="border p-2">
                  {new Date(item.requestDate).toLocaleDateString()}
                </td>
                <td className="connect-button">
                  <button
                    onClick={() => handleConnect(item)}
                    className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 disabled:bg-gray-400"
                    disabled={
                      (item.status !== "APPROVED" && item.status !== "PENDING") || 
                      item.createdBy === hospitalId
                    }
                  >
                    Connect
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="9" className="text-center p-2">No data available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default RequestTable;
