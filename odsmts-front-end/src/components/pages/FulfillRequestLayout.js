import Sidebar from "../sidebar/sidebar";
import FulfillRequest from "../pages/create form/FulfillRequest"; // Your new component
import Header from "../pages/Header/header";

const FulfillRequestLayout = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <Header />  {/* Maintains consistent header */}
        <div className="connect-hospital-content">
          <FulfillRequest />
        </div>
      </div>
    </div>
  );
};

export default FulfillRequestLayout;