import React, { useState, useEffect, Component } from "react";
import { NavLink } from "react-router-dom";
import "./SignUp.scss";
import facade from "../../services/apiFacade";

function SignUp() {
  const initialState = { user_name: "", user_email: "", user_pass: "" };
  const [user, setUser] = useState(initialState);
  console.log({user})

  const handleChange = event => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = event => {
    event.preventDefault();
    facade.createUser(user.user_name, user.user_email, user.user_pass);
    setUser(initialState);
    window.alert("You have created a user");
  };

  useEffect(() => {
    document.body.classList.add("centered");
    return () => {
      document.body.classList.remove("centered");
    };
  });

  return (
    <div className="centered">
      <section className="section section-signup">
        <div className="signup-block">
          <div className="block-inner">
            <h1>Sign Up</h1>
            <form>
              <div className="form-group form-group-icon form-group-username">
                <input
                  type="text"
                  name="user_name"
                  value={user.user_name}
                  onChange={handleChange}
                  className="form-control form-control-lg"
                  placeholder="Your username"
                  id="user_name"
                />
              </div>
              <div className="form-group form-group-icon form-group-email">
                <input
                  type="email"
                  name="user_email"
                  value={user.user_email}
                  onChange={handleChange}
                  className="form-control form-control-lg"
                  placeholder="Your email"
                  id="user_email"
                />
              </div>
              <div className="form-group form-group-icon form-group-password">
                <input
                  type="password"
                  name="user_pass"
                  value={user.user_pass}
                  onChange={handleChange}
                  className="form-control form-control-lg"
                  placeholder="Your password"
                  id="user_pass"
                />
              </div>

              <div className="terms">
                By creating an account, you agree and accept our{" "}
                <NavLink to="/terms">Terms</NavLink> and{" "}
                <NavLink to="/terms">Privacy Policy</NavLink>.
              </div>

              <div className="btn-w">
                <button className="btn btn-red btn-lg btn-block" onClick={handleSubmit}  type="submit">
                  Sign in
                </button>
              </div>
              <p>{JSON.stringify(user)}</p>
              <div className="registered">
                Already have an account? <NavLink to="/signIn">Log In</NavLink>.
              </div>
            </form>
          </div>
        </div>
      </section>
    </div>
  );
}

export default SignUp;
