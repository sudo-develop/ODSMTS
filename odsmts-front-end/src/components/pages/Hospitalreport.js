import Sidebar from "../sidebar/sidebar";
import HospitalReportTable from "../tables/hospital-report-table";
import Header from "../pages/Header/header"; // ✅ Import Header

const HospitalReport = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
      <Header />  {/* ✅ Display hospital info */}
      <br/>
      <HospitalReportTable />
      </div>
    </div>
  );
};

export default HospitalReport;