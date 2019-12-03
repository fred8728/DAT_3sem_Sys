import React from 'react';
import { Link } from 'react-router-dom';
import './Logo.scss';
import fsLogo from '../../../assets/images/logo.png';

const Logo = () => {
  return (
    <div className="logo">
      <Link to='/'>
        <img src={fsLogo} alt="Awesome Recipes"/>
      </Link>
    </div>
  );
};

export default Logo;


