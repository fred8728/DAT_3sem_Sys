import React from "react";
import { BrowserRouter, Switch, NavLink, Route } from "react-router-dom";
import Recipes from "./Recipes"
import Addrecipe from "./addRecipe";
import SearchPage from "./SearchPage";
import Profile from "./Profile"
import Login from "./Login"
import HomemadeRecipes from './HomemadeRecipes'
import CreateUser from "./CreateUser";


const NoMatch = () => <div>Urlen matcher ingen kendte routes</div>;
const Home = () => <div></div>

function App({ apiFacade }) {

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
        <NavLink activeClassName="active" to="/homemade">
          Homemade Recipes
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
      <li>
        <NavLink activeClassName="active" to="/Search">
          Search
        </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/addRecipe">
          ADD Recipes here
          </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/profile">
          Profile
        </NavLink>
      </li>
    </ul>
  );
}

function Content() {

  return (
    <Switch>
      <Route exact path="/">
        <Home />
      </Route>
      <Route path="/recipes">
        <Recipes />
      </Route>
      <Route path="/homemade">
        <HomemadeRecipes />
      </Route>
      <Route path="/signIn">
        <Login />
      </Route>
      <Route path="/signUp">
        <CreateUser />
      </Route>
      <Route path="/Search">
        <SearchPage />
      </Route>
      <Route path="/addrecipe">
        <Addrecipe />
      </Route>
      <Route path="/profile">
        <Profile />
      </Route>
      <Route path="*">
        <NoMatch />
      </Route>
    </Switch>
  );
}

export default App;
