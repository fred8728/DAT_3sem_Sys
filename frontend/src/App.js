import React from "react";
import { BrowserRouter, Switch, NavLink, Route } from "react-router-dom";
import Recipes from "./components/Recipes";
import AddRecipe from "./components/addRecipe";
import Profile from "./components/Profile";
import Login from "./components/Login";
import HomemadeRecipes from "./components/HomemadeRecipe";
import SignUp from "./components/SignUp";
import Header from "./components/navigation/Header";
import Footer from "./components/navigation/Footer"
import HomePage from "./components/HomePage"


const NoMatch = () => <div>Urlen matcher ingen kendte routes</div>;

function App({ apiFacade }) {
  return (
    <BrowserRouter>
    <Header/>
            
            <main className="main"><div className="container">
                <Content apiFacade={apiFacade} />
            </div></main>
    
            <Footer />
    </BrowserRouter>
  );
}
// <SearchPage /> under search
function Content(props) {
  return (
    <Switch>
      <Route exact path="/">
        <HomePage />
      </Route>
      <Route path="/recipes">
        <Recipes />
      </Route>
      <Route path="/homemade">
        <HomemadeRecipes/>
      </Route>
      <Route path="/search">
        
      </Route>
      <Route path="/signIn">
        <Login />
      </Route>
      <Route path="/signup" exact>
        <SignUp/>
      </Route>
      <Route path="/profile" exact>
        <Profile/>
      </Route>
      <Route path="/add-recipe" exact>
        <AddRecipe/>
      </Route>
      <Route path="/about" render={() => <h1>About Us</h1>} exact />
      <Route path="/terms" render={() => <h1>Terms</h1>} exact />
      <Route path="/privacy" render={() => <h1>Privacy Policy</h1>} exact />

      <Route path="*">
        <NoMatch />
      </Route>
    </Switch>
  );
}

export default App;
