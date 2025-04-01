import Sidebar from "../sidebar/sidebar";
import RequestByIdTable from "../tables/request-by-id-table";
import Header from "../pages/Header/header"; // ✅ Import Header

const HospitalRequests = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <Header />  {/* ✅ Display hospital info */}
        {/* <h1>Hospital Dashboard</h1> */}
        <br />
        <RequestByIdTable />
      </div>
    </div>
  );
};

export default HospitalRequests;