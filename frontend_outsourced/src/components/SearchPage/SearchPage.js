import React, { useState, useEffect } from "react";
import { BrowserRouter, useRouteMatch, Switch, NavLink, Route, Link } from "react-router-dom";
//import InputField from "@kiwicom/orbit-components/lib/InputField";
import apiFacade from "../../services/apiFacade";
import Login from "../Login";
import LoggedIn from "../LoggedIn";
import Recipes from "../Recipes";
import AllRecipes from "../Recipes";
import SearchForm from '../SearchForm';

const SearchPage = () => {

  const [recipes, setRecipes] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  let match = useRouteMatch();
  const handleChange = e => {
    setSearchTerm(e.target.value);
    console.log(recipes)
  };
  useEffect(() => {
    apiFacade.getRecipes().then(data => {
      setRecipes(data.results)
      console.log("check data", data.results)
    });
  }, [searchTerm]);

  // implement a filter method which takes an array and can filter the
  // the values it needs and filter out them i dont need.
  return (
    <section className="section section-recipes">
      <h1>Search</h1>
      
      <SearchForm value={searchTerm} onChange={handleChange} />

      <div className="recipe-list">
      {recipes
        .filter(recipe => {
          return (
            recipe.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
            recipe.ingredients.toLowerCase().includes(searchTerm.toLowerCase())
          )
        }
        )
        .map((data, index) => (
          
          <div className="item" key={index}>
            <div className="item-img">{<img src={data.thumbnail} />}</div>
            <div className="item-r">
              <div className="item-title">{data.title}</div>
              <div className="item-info">
                <div className="portions">
                  <i className="icon icon-portion"></i>
                  <span>4 portions</span>
                </div>
                <div className="time">
                  <i className="icon icon-time"></i>
                  <span>1 h 30 m</span>
                </div>
              </div>
              
            </div>
            <div className="item-links">
                <Link className="btn btn-green btn-sm btn-block" to={`${match.url}/${data.title}/${data.ingredients}/${data.href}`}>
                  Details
                </Link>
                <Link className="btn btn-grey btn-sm btn-block" to={data.href}>Link</Link>
            </div>
          </div>
        ))}
      </div>

    </section>
  );
}

export default SearchPage;