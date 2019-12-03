import React from 'react';
import Logo from '../Logo';
import Toggler from '../Toggler';
import Topmenu from '../Topmenu';
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


