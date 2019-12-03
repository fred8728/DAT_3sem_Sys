import React from 'react';
import { NavLink } from "react-router-dom";


const Topmenu = (props) => {
  return (
    <nav className="topmenu" id="topmenu">
      <ul className="nav">
        {/* <li>
          <NavLink exact activeClassName="active" to="/">
            Home
          </NavLink>
        </li> */}
        <li>
          <NavLink activeClassName="active" to="/recipes">
            Recipes
          </NavLink>
        </li>
        <li>
          <NavLink activeClassName="active" to="/homemade">
            Homemade Recipes
          </NavLink>
        </li>
        <li>
          <NavLink activeClassName="active" to="/search">
            Book Search
          </NavLink>
        </li>
        <li className="nav-login">
          <NavLink activeClassName="active" to="/signIn">
            Sign in
          </NavLink>
        </li>
        <li className="nav-signup">
          <NavLink activeClassName="active" to="/signup">
            Sign up
          </NavLink>
        </li>
        <li className="nav-profile">
          <NavLink activeClassName="active" to="/profile">
            Profile
          </NavLink>
        </li>
        <li className="nav-logout">
          <NavLink to="/">
            Logout
          </NavLink>
        </li>
      </ul>
    </nav>
  )
}

export default Topmenu;