import React, { useState, useEffect } from 'react'
import facade from './apiFacade'
import Login from "./Login"
import Profile from './Profile'

function LoggedIn(props) {

  return (
    <div>
      <Profile />
    </div>
  )
}

export default LoggedIn;