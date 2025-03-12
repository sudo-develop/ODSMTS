import { useState } from "react";
import { useDispatch } from "react-redux";
import { loginSuccess } from "../../redux/userSlice"; // Adjusted path
import { loginUser } from "../../api";  // Corrected path
import { useNavigate } from "react-router-dom";
import { FaEye, FaEyeSlash } from "react-icons/fa"; // Import eye icons
import "../styles/login-style.css";  // Corrected path
import loginImage from "../../assets/logo-image.png";  // Corrected path

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // const handleLogin = async (e) => {
  //   e.preventDefault();
  //   try {
  //     const response = await loginUser(username, password);
  //     console.log("Login Successful:", response);
  
  //     dispatch(loginSuccess(response));
  
  //     // Role-based redirection
  //     if (response.roleId === 2) {
  //       navigate("/admin-dashboard");
  //     } else if (response.roleId === 3) {
  //       navigate("/hospital-dashboard");
  //     } else {
  //       navigate("/default-dashboard"); // Handle unknown roles
  //     }
  //   } catch (err) {
  //     console.error("Login Failed:", err);
  //     setError(err.message || "Login failed");
  //   }
  // };
  

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await loginUser(username, password); // API call
      console.log("Login Successful:", response);
      
      // Dispatch full user data to Redux
      dispatch(loginSuccess({
        token: response.token,
        username: response.username,
        email: response.email,  // ✅ Store email
        roleId: response.roleId,
        hospitalId: response.hospitalId,
        hospitalDetails: response.hospitalDetails,  // ✅ Store hospital details
      }));
  
      // Role-based redirection
      if (response.roleId === 2) {
        navigate("/admin-dashboard");
      } else if (response.roleId === 3) {
        navigate("/hospital-dashboard");
      } else {
        navigate("/default-dashboard"); // Handle unknown roles
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
            <input 
              type="text" 
              value={username} 
              onChange={(e) => setUsername(e.target.value)} 
            />
          </div>
          <div className="input-group password-container">
            <label>Password</label>
            <button 
              type="button" 
              className="eye-button"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? <FaEyeSlash /> : <FaEye />}
            </button>
            <input 
              type={showPassword ? "text" : "password"} 
              value={password} 
              onChange={(e) => setPassword(e.target.value)} 
            />
            {/* <button 
              type="button" 
              className="eye-button"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? <FaEyeSlash /> : <FaEye />}
            </button> */}
          </div>
          <button className="login-button" type="submit">Login</button>
        </form>
        {error && <p className="error-text">{error}</p>}
      </div>

      {/* Right Side Image */}
      <div className="image-container">
        <img src={loginImage} alt="Login Illustration" />
      </div>
    </div>
  );
};

export default Login;
