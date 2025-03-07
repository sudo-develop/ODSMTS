import Sidebar from "../sidebar/sidebar";
import HospitalReportTable from "../tables/hospital-report-table";


const HospitalReport = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
      <h1>Hospital Report</h1>
      <br/>
      <HospitalReportTable />
      </div>
    </div>
  );
};

export default HospitalReport;