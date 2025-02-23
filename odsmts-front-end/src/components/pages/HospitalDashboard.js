import Sidebar from "../sidebar/sidebar";
import RequestTable from "../tables/request-table";
import "../styles/request-table.css";

const HospitalDashboard = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
      <h1>Hospital Dashboard</h1>
      <p>Welcome to the hospital management system!</p>
      <br/>
      <RequestTable />
      </div>
    </div>
  );
};

export default HospitalDashboard;
