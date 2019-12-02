import React,{useState, useEffect} from 'react'
import facade from './apiFacade'
import {Route, Link, useRouteMatch, useParams
} from "react-router-dom";
import SearchForm from './SearchForm'

function CustomRecipe(){
const [customRecipe, setCustomRecipe] = useState([]);
let match = useRouteMatch();

useEffect(() =>{
facade.getHomemadeRecipes().then(data => { setCustomRecipe(data)})
},[])
console.log("check data", customRecipe)
return(
    <>
    <section className="section section-recipes">
      <h1>Recipes</h1>
      
      <div className="recipe-list">
          {customRecipe
            .map((data, index) => (
              <div className="item" key={index}>
            <div className="item-r">
              <div className="item-c">
              <div className="item-title">{data.name}</div>
                <div className="item-info">
                  <div className="portions">
                    <i className="icon icon-portion"></i>
                    <span>Portion: {data.portion_size}</span>
                  </div>
                  <div className="time">
                    <i className="icon icon-time"></i>
                    <span>Time: {data.cooking_time}</span>
                  </div>
                </div>
              </div>
            </div>
            <div className="item-links">
                <Link className="btn btn-green btn-sm btn-block" to={`${match.url}/${data.name}/${data.cooking_time}/${data.portion_size}/${data.ingredients}/${data.description}`}>
                  Details
                </Link>
              </div> 
          </div>
        ))}
      </div>
      { <Route path={`${match.path}/:name/:time/:portion/:ingredients/:description`}>
        <Recipe></Recipe>
      </Route> }
    </section>
    </>
  );
}

function Recipe() {
  let { user, name, time, portion, ingredients, description } = useParams();
  return (
    <div>
      
      <p>Recipe: {name}</p>
      <p>Time: {time}</p>
      <p>Portion: {portion}</p>
      <p>Ingredients: {ingredients}</p>
      <p>Description: {description}</p>
      <br></br>
    </div>
  )
}
export default CustomRecipe;