import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import ExcelJS from "exceljs";
import { jsPDF } from "jspdf";
import autoTable from "jspdf-autotable";
import "jspdf-autotable";
import { fetchRequestsByHospital, HospitalInventory } from "../../api";
import "../styles/reportbutton.css"; // CSS file for button styling

const HospitalReport = () => {
  const [inventoryData, setInventoryData] = useState([]);
  const [requestData, setRequestData] = useState([]);
  const token = useSelector((state) => state.user.token);
  const hospitalId = useSelector((state) => state.user.hospitalId);

  // Fetch Inventory Data
  useEffect(() => {
    const fetchInventory = async () => {
      try {
        if (hospitalId) {
          const result = await HospitalInventory(hospitalId, token);
          setInventoryData(result);
        }
      } catch (error) {
        console.error("Error fetching inventory data:", error);
      }
    };
    fetchInventory();
  }, [hospitalId, token]);

  // Fetch Request Data and map only required fields
  useEffect(() => {
    const fetchRequests = async () => {
      try {
        if (hospitalId) {
          const result = await fetchRequestsByHospital(hospitalId, token);
          const formattedRequests = Array.isArray(result)
            ? result.map((item) => ({
                hospitalName: item.hospitalName,
                drugName: item.drugName,
                formName: item.formName,
                quantity: item.quantity,
                fulfilledByName: item.fulfilledByName,
                fulfilledQuantity: item.fulfilledQuantity,
                status: item.status,
                requestDate: item.requestDate,
              }))
            : [];
          setRequestData(formattedRequests);
        }
      } catch (error) {
        console.error("Error fetching request data:", error);
      }
    };
    fetchRequests();
  }, [hospitalId, token]);

  // Generic function to generate an Excel report using ExcelJS.
  const generateExcel = async (data, reportType, headers, rowMapper) => {
    const workbook = new ExcelJS.Workbook();
    const worksheet = workbook.addWorksheet(reportType);
    worksheet.addRow(headers);
    data.forEach((item) => {
      worksheet.addRow(rowMapper(item));
    });
    const buffer = await workbook.xlsx.writeBuffer();
    const blob = new Blob([buffer], {
      type:
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
    });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = `${reportType}.xlsx`;
    a.click();
    window.URL.revokeObjectURL(url);
  };

  // Generic function to generate a PDF report using jsPDF.
  // const generatePDF = (data, reportType, headers, rowMapper) => {
  //   const doc = new jsPDF();
  //   const rows = data.map(rowMapper);
  //   doc.autoTable({
  //     head: [headers],
  //     body: rows,
  //   });
  //   doc.save(`${reportType}.pdf`);
  // };

  const generatePDF = (data, reportType, headers, rowMapper) => {
    const doc = new jsPDF();
    const rows = data.map(rowMapper);
    // Call autoTable as a function with doc passed in
    autoTable(doc, {
      head: [headers],
      body: rows,
    });
    doc.save(`${reportType}.pdf`);
  };

  // Inventory Report configuration
  const inventoryHeaders = [
    "Hospital",
    "Drug",
    "Form",
    "Qty. Per Unit",
    "Expiry Date",
    "Count",
  ];
  const mapInventoryRow = (item) => [
    item.hospitalName,
    item.drugName,
    item.formName,
    item.quantityPerUnit,
    item.expiryDate,
    item.count,
  ];

  // Request Report configuration
  const requestHeaders = [
    "Hospital",
    "Drug",
    "Form",
    "Quantity",
    "Fulfilled By",
    "Fulfilled Quantity",
    "Status",
    "Request Date",
  ];
  const mapRequestRow = (item) => [
    item.hospitalName,
    item.drugName,
    item.formName,
    item.quantity,
    item.fulfilledByName,
    item.fulfilledQuantity,
    item.status,
    item.requestDate,
  ];

  return (
    <div className="report-container">
      <div className="report-section">
        <h2 className="report-header">Inventory Report</h2>
        <div className="button-group">
          <button
            className="rounded-button"
            onClick={() =>
              generateExcel(
                inventoryData,
                "Inventory_Report",
                inventoryHeaders,
                mapInventoryRow
              )
            }
          >
            Download Inventory Excel
          </button>
          <button
            className="rounded-button"
            onClick={() =>
              generatePDF(
                inventoryData,
                "Inventory_Report",
                inventoryHeaders,
                mapInventoryRow
              )
            }
          >
            Download Inventory PDF
          </button>
        </div>
      </div>
      <div className="report-section">
        <h2 className="report-header">Request Report</h2>
        <div className="button-group">
          <button
            className="rounded-button"
            onClick={() =>
              generateExcel(
                requestData,
                "Request_Report",
                requestHeaders,
                mapRequestRow
              )
            }
          >
            Download Request Excel
          </button>
          <button
            className="rounded-button"
            onClick={() =>
              generatePDF(
                requestData,
                "Request_Report",
                requestHeaders,
                mapRequestRow
              )
            }
          >
            Download Request PDF
          </button>
        </div>
      </div>
    </div>
  );
  
  
};

export default HospitalReport;
