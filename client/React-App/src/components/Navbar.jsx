import React, { useState } from "react";
import "../styles/Navbar.css";
import logo from "../assets/logo-react.png";

import profile from "../assets/profile-pic.jpg";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [menu, setMenu] = useState("Home");
  return (
    <div className="navbar ps-5 pe-5">
      <img src={logo} alt="" className="logo" />
      <ul>
        <Link to="/" style={{ textDecoration: "none" }}>
          <li
            onClick={() => {
              setMenu("Home");
            }}
            className={menu === "Home" ? "active" : "list-item"}
          >
            Home
          </li>
        </Link>
        <Link to="/PropertyStatus" style={{ textDecoration: "none" }}>
          <li
            onClick={() => {
              setMenu("UserRequest");
            }}
            className={menu === "UserRequest" ? "active" : "list-item"}
          >
            User Request
          </li>
        </Link>
        <Link to="/UserRequest" style={{ textDecoration: "none" }}>
          <li
            onClick={() => {
              setMenu("About");
            }}
            className={menu === "About" ? "active" : "list-item"}
          >
            About
          </li>
        </Link>
      </ul>

      <div className="Profile-btn">
        <div className="Entry">
          <button>
            <Link to="/login" style={{ textDecoration: "none" ,color:'black' }}>
              Logout
            </Link>
          </button>
        </div>

        <div className="Profile">
        <Link to="/profile" style={{ textDecoration: "none", color:'black' }}>
              
           
          <img src={profile} alt="profile" className="profile-pic" />
          <p className="seller-name">Stitch</p> </Link>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
