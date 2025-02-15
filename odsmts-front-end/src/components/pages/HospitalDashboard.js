import HospitalSidebar from "../sidebar/HospitalSidebar";

const HospitalDashboard = () => {
  return (
    <div className="dashboard-container">
      <HospitalSidebar />
      <div className="content">
        <h1>Hospital Dashboard</h1>
        <p>Welcome to the hospital management system!</p>
      </div>
    </div>
  );
};

export default HospitalDashboard;
