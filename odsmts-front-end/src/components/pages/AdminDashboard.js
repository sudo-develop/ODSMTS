import AdminSidebar from "../sidebar/AdminSidebar";
import "../styles/dashboard-style.css";

const AdminDashboard = () => {
  return (
    <div className="dashboard-container">
      <AdminSidebar />
      <div className="content">
        <h1>Admin Dashboard</h1>
        <p>Welcome, Admin!</p>
      </div>
    </div>
  );
};

export default AdminDashboard;
