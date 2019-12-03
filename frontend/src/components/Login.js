import facade from "./apiFacade";
import React, { useState, useEffect} from "react";
import { NavLink } from "react-router-dom";
import "../scss/Login.scss";
import LoggedIn from "./LoggedIn";

function LogIn(props) {
  const [user, setUser] = useState();

  const login = event => {
    event.preventDefault();
    props.login(user.username, user.password);
  };
  const onChange = event => {
    setUser({ ...user, [event.target.id]: event.target.value });
  };

  return (
    <div className="centered">
      <section className="section section-login">
        <div className="login-block">
          <div className="block-inner">
            <h1>Login</h1>
            <form onSubmit={login} onChange={onChange}>
              <div className="form-group form-group-icon form-group-username">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="Your username"
                  id="username"
                />
              </div>
              <div className="form-group">
                <div className="form-group-icon form-group-password">
                  <input
                    type="password"
                    className="form-control form-control-lg"
                    placeholder="Your password"
                    id="password"
                  />
                </div>
              </div>

              <div className="btn-w">
                <button className="btn btn-red btn-lg btn-block" type="submit">
                  Log In
                </button>
              </div>

              <div className="not-registered">
                Not registered?{" "}
                <NavLink to="/signup">Create an account</NavLink>.
              </div>
            </form>
          </div>
        </div>
      </section>
    </div>
  );
}

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  const logout = () => {
    facade.logout();
    setLoggedIn(false);
  };

  const login = (user, pass) => {
    facade
      .login(user, pass)
      .then(res => {
        setLoggedIn(true);
      })
      .catch(e => window.alert("Wrong username or password!"));
  };
  return (
    <div>
      {!loggedIn ? (
        <div>
          {" "}
          <LogIn login={login} />
        </div>
      ) : (
        <div>
          <LoggedIn />
          <button onClick={logout}>Logout</button>
        </div>
      )}
    </div>
  );
}
export default App;
