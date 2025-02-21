import { Link } from "react-router-dom";

const AdminSidebar = () => {
  return (
    <div className="sidebar">
      <h2>Admin Panel</h2>
      <ul>
        <li><Link to="/admin-dashboard">Dashboard</Link></li>
        <li><Link to="/manage-users">Manage Users</Link></li>
        <li><Link to="/reports">Reports</Link></li>
      </ul>
    </div>
  );
};

export default AdminSidebar;
