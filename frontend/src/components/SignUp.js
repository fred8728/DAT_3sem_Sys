import React, { useState, useEffect } from 'react'
import { NavLink } from "react-router-dom";
import facade from './apiFacade'
import "../scss/SignUp.scss"



function CreateUser() {

  const initialState = { user_name: "", user_email: "", user_pass: "" }
  const [user, setUser] = useState(initialState);

  const handleChange = event => {
    const target = event.target;
    const name = target.name;
    const value = target.value;
    setUser({ ...user, [name]: value })
  }

  const handleSubmit = event => {
    event.preventDefault();
    facade.createUser(user.name, user.email, user.password)
    setUser(initialState)
    window.alert("You have created a user")
  }

  return (
    <div className="centered">
      <section className="section section-signup">
        <div className="alert alert-light">
          Something goes wrong. Please, try again later.
        </div>
        <div className="signup-block">
          <div className="block-inner">
            <h1>Sign Up</h1>
            <form handleSubmit={handleSubmit} handleChange={handleChange}>
              <div className="form-group form-group-icon form-group-username">
                <input
                  type="text"
                  name="username"
                  value={user.name}
                  onChange={handleChange}
                  className="form-control form-control-lg"
                  placeholder="Your username"
                  id="username"
                />
              </div>
              <div className="form-group form-group-icon form-group-email">
                <input
                  type="email"
                  name="email"
                  value={user.email}
                  onChange={handleChange}
                  className="form-control form-control-lg"
                  placeholder="Your email"
                  id="email"
                />
              </div>
              <div className="form-group form-group-icon form-group-password">
                <input
                  type="password"
                  name="password"
                  value={user.password}
                  onChange={handleChange}
                  className="form-control form-control-lg"
                  placeholder="Your password"
                  id="password"
                />
              </div>

              <div className="terms">
                By creating an account, you agree and accept our{" "}
                <NavLink to="/terms">Terms</NavLink> and{" "}
                <NavLink to="/terms">Privacy Policy</NavLink>.
              </div>

              <div className="btn-w">
                <button className="btn btn-red btn-lg btn-block" type="submit" onClick={handleSubmit}>
                  Log In
                </button>
              </div>
              <p>{JSON.stringify(user)}</p>
              <div className="registered">
                Already have an account? <NavLink to="/signIn">
                  Sign in</NavLink>.
              </div>
            </form>
          </div>
        </div>
      </section>
    </div>
  );
}


export default CreateUser;