import React, { useState, useEffect } from 'react'
import facade from './apiFacade'
import oggedIn from "./LoggedIn"
import Login from "./Login"
import apiFacade from "./apiFacade"

function LoggedIn() {
  const [username, setUserName] = useState();
  const [loggedIn, setLoggedIn] = useState(false);
  const [error, setError] = useState();

  const logout = () => {
    apiFacade.logout();
    setLoggedIn({ loggedIn: false });
  };

  const login = (user, pass) => {
    apiFacade.login(user, pass)
      .then(response => {
        setLoggedIn(true);
        setError("");
      })
      .catch(err => setError("Username or password is incorrect. Try again!"));
  };

  useEffect(() => {
    facade.fetchData().then(data => { setUserName(data.username) })
  }, [])

  return (

    <div>
      {!loggedIn.loggedIn ? (
        <div>
          <Login login={login} />
          <p>{error}</p>
        </div>
      ) : (
          <div>
            <div>
              <h2>Data Received from server</h2>
              <h3>{username}</h3>
            </div>
            <br></br>
            <button onClick={logout}>Sign out</button>
          </div>
        )}
    </div>

  )

}

export default LoggedIn;