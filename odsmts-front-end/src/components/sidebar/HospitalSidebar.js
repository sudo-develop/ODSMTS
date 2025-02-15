import { Link } from "react-router-dom";

const HospitalSidebar = () => {
  return (
    <div className="sidebar">
      <h2>Hospital Panel</h2>
      <ul>
        <li><Link to="/hospital-dashboard">Dashboard</Link></li>
        <li><Link to="/patients">Patients</Link></li>
        <li><Link to="/appointments">Appointments</Link></li>
        <li><Link to="/reports">Reports</Link></li>
      </ul>
    </div>
  );
};

export default HospitalSidebar;
