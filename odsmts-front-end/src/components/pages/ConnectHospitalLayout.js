import Sidebar from "../sidebar/sidebar";
import ConnectHospital from "../pages/create form/ConnectHospitals"; // Your new component
import Header from "../pages/Header/header";

const ConnectHospitalLayout = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <Header />  {/* Maintains consistent header */}
        <div className="connect-hospital-content">
          <ConnectHospital />
        </div>
      </div>
    </div>
  );
};

export default ConnectHospitalLayout;