import Sidebar from "../sidebar/sidebar";
import "../styles/dashboard-style.css";

const AdminDashboard = () => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <div className="content">
        <h1>Admin Dashboard</h1>
        <p>Welcome, Admin!</p>
      </div>
    </div>
  );
};

export default AdminDashboard;
