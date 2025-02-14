import { useState } from "react";
import { loginUser } from "../api";  // Ensure correct API import
import "./styles/login-style.css";  // Import the CSS file
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [userData, setUserData] = useState(null);  // Store API response
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await loginUser(username, password);
      console.log("Login Successful:", response);
      
      // Store response in state or localStorage
      setUserData(response);
      localStorage.setItem("token", response.token);  // Save JWT token
      localStorage.setItem("username", response.username);
      localStorage.setItem("roleId", response.roleId);

      if (response.roleId === 2) {
        navigate("/admin-dashboard");
      } else {
        navigate("/user-dashboard");
      }

    } catch (err) {
      console.error("Login Failed:", err);
      setError(err.message || "Login failed");
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">Login</h2>
        <form onSubmit={handleLogin}>
          <div className="input-group">
            <label>Username</label>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
          </div>
          <div className="input-group">
            <label>Password</label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>
          <button type="submit">Login</button>
        </form>
        {error && <p className="error-text">{error}</p>}
        {userData && <p className="success-text">Welcome, {userData.username}! Role ID: {userData.roleId}</p>}
      </div>
    </div>
  );
};

export default Login;
