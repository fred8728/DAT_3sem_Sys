import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";
import { //BrowserRouter,
  useRouteMatch, Link
} from "react-router-dom";
import SearchForm from './SearchForm'

const AllRecipes = () => {

  const [recipes, setRecipes] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  let match = useRouteMatch();
  const handleChange = e => {
    setSearchTerm(e.target.value);
    console.log(recipes)
  };
  useEffect(() => {
    apiFacade.getRecipes().then(data => {
      const x = data.results.sort(function (a, b) {
        var nameA = a.title.toLowerCase(), nameB = b.title.toLowerCase();
        if (nameA < nameB) //sort string ascending
          return -1;
        if (nameA > nameB)
          return 1;
        return 0; //default return value (no sorting)
      });
      setRecipes(x)
      console.log("check data", data.results)
    });
  }, [searchTerm]);
  
  return (
    <>
    <section className="section section-recipes">
      <h1>Recipes</h1>
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
              <div className="item-c">
              <div className="item-title">{data.title}</div>
                <div className="item-info">
                  <div className="portions">
                    <i className="icon icon-portion"></i>
                    <span>{data.ingredients}</span>
                  </div>
                </div>
              </div>
              <div className="item-links">
                <a className="btn btn-grey btn-sm btn-block" href={data.href}> Link </a>
              </div>
            </div>
            
          </div>
        ))}
      </div>
      
    </section>
    </>
  );
}

export default AllRecipes;