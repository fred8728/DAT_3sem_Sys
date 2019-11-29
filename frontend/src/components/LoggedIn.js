import React, { useState, useEffect } from 'react'
import facade from './apiFacade'
import Login from "./Login"

function LoggedIn() {
  const [username, setUserName] = useState();
  const [loggedIn, setLoggedIn] = useState(false);
  const [error, setError] = useState();

  const logout = () => {
    facade.logout();
    setLoggedIn({ loggedIn: false });
  };

  const login = (user, pass) => {
    facade.login(user, pass)
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
            <button onClick={logout}>Sign out</button>
          </div>
        )}
    </div>

  )

}

export default LoggedIn;