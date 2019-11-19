import React, { useState, useEffect } from "react";
import { BrowserRouter, Switch, NavLink, Route } from "react-router-dom";
import apiFacade from "./apiFacade";
import Login from "./Login";
import LoggedIn from "./LoggedIn";
import Recipes from "./Recipes"
import AllRecipes from "./Recipes";

const NoMatch = () => <div>Urlen matcher ingen kendte routes</div>;
const Home = () => <div>Home</div>;
const Company = () => <div>Company</div>;

function App({apiFacade}) {

  return (
    <BrowserRouter>
      <Header />
      <Content apiFacade={apiFacade} />
    </BrowserRouter>
  );
}

function Header() {
  return (
    <ul className="header">
      <li>
        <NavLink exact activeClassName="active" to="/">
          Home
        </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/recipes">
          Recipes
        </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/signIn">
          Sign In
        </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/signUp">
          Sign up
        </NavLink>
      </li>
    </ul>
  );
}

function Content(props) {
const {recipes, loggedIn} = props;

  return (
    <Switch>
      <Route exact path="/">
        <Home />
      </Route>
      <Route path="/recipes">
        <Recipes />
      </Route>
      <Route path="/signIn">
        <Login loggedIn={loggedIn} />
      </Route>
      <Route path="/signUp">
      </Route>
      <Route path="*">
        <NoMatch />
      </Route>
    </Switch>
  );
}

export default App;
