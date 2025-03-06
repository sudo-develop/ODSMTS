import Sidebar from "../sidebar/sidebar";
import HospitalInventoryTableTable from "../tables/hospital-inventory-table";


const HospitalDashboard = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
      <h1>Hospital Inventory</h1>
      <br/>
      <HospitalInventoryTableTable />
      </div>
    </div>
  );
};

export default HospitalDashboard;
