import Sidebar from "../sidebar/sidebar";
import HospitalInventoryTableTable from "../tables/hospital-inventory-table";
import Header from "../pages/Header/header"; // ✅ Import Header

const HospitalDashboard = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
      <Header />  {/* ✅ Display hospital info */}
        <br />
      <HospitalInventoryTableTable />
      </div>
    </div>
  );
};

export default HospitalDashboard;
