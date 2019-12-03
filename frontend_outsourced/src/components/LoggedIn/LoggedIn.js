import React, { useState, useEffect } from 'react';
import apiFacade from "../../services/apiFacade";
import './LoggedIn.scss';

import Profile from "../Profile/Profile"

function LoggedIn(props) {

  return (
    <div>
      <Profile />
    </div>
  )
}

export default LoggedIn;