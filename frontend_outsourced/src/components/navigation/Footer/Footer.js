import React from 'react';
import { NavLink } from 'react-router-dom';
import './Footer.scss';



const Footer = (props) => {
  return (
    <div className="footer-wrapper">
      <div className="show-footer">Info</div>
      <footer className="footer">
        <div className="container">
          <div className="footer-l">
            <p>AwesomeRecipes &copy; 2019</p>
          </div>
          <div className="footer-r">
            <nav className="bottommenu">
              <ul className="nav">
                <li>
                  <NavLink exact activeClassName="active" to="/about">
                    About Us
                  </NavLink>
                </li>
                <li>
                  <NavLink exact activeClassName="active" to="/Terms">
                    Terms
                  </NavLink>
                </li>
                <li>
                  <NavLink exact activeClassName="active" to="/privacy">
                    Privacy Policy
                  </NavLink>
                </li>
              </ul>
            </nav>
          </div>
          
        </div>
      </footer>
    </div>
  );
};

export default Footer;


