import { Link } from "react-router-dom";
import "../styles/sidebar.css";
import { useSelector } from "react-redux";

const Sidebar = () => {
    const roleId = useSelector((state) => state.user.roleId);
    return (
        <div>
          {roleId === 2 && ( 
            <div className="sidebar">
              <h2>Admin Panel</h2>
              <ul>
                <li><Link to="/admin-dashboard">Dashboard</Link></li>
                <li><Link to="/reports">Reports</Link></li>
              </ul>
            </div>
          )}

          {roleId === 3 && ( 
            <div className="sidebar">
              <h2>Hospital Panel</h2>
              <ul>
                <li><Link to="/admin-dashboard">Dashboard</Link></li>
                <li><Link to="/manage-users">Manage Users</Link></li>
                <li><Link to="/reports">Reports</Link></li>
              </ul>
            </div>
          )}
        </div>
      );
};

export default Sidebar;

