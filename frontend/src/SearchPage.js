import React, { useState, useEffect } from "react";
import { BrowserRouter, Switch, NavLink, Route } from "react-router-dom";
//import InputField from "@kiwicom/orbit-components/lib/InputField";
import apiFacade from "./apiFacade";
import Login from "./Login";
import LoggedIn from "./LoggedIn";
import Recipes from "./Recipes";
import AllRecipes from "./Recipes";

export default function SearchPage() {
  // implement a filter method which takes an array and can filter the
  // the values it needs and filter out them i dont need.
  return (
    <div>
      <form>
        <p>
        <input type="text" /> <label> insert a search value</label>
        </p>
        {/* <p> test </p> */}
        <p>
        <input type ="button" value="press here"/></p>
        <div>
        {/* implemt a return method here */}

        </div>
      </form>
    </div>
  );
}
