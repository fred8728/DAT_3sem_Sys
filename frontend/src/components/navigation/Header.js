import React from 'react';
import Logo from '../navigation/Logo';
import Toggler from '../navigation/Toggler';
import Topmenu from '../navigation/Topmenu';
import './Header.scss';

const Header = () => {
  return (
    <header className="header">
      <div className="container">
        <Logo />
        <Topmenu />
        <Toggler />
      </div>
    </header>
  );
};

export default Header;


