import Sidebar from "../sidebar/sidebar";
import RequestTable from "../tables/request-table";
import Header from "../pages/Header/header"; // ✅ Import Header

const HospitalDashboard = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <Header />  {/* ✅ Display hospital info */}
        {/* <h1>Hospital Dashboard</h1> */}
        <br />
        <RequestTable />
      </div>
    </div>
  );
};

export default HospitalDashboard;
