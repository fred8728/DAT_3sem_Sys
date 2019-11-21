import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";
import { BrowserRouter, useRouteMatch, useParams, Route, Link } from "react-router-dom";

const AllRecipes = () =>{
  const [recipes, setRecipes] = useState([]);
  let match = useRouteMatch();

  useEffect(() => {
    
      apiFacade.getRecipes().then(data => {setRecipes(data.results)
      console.log("check data",data)});
  }, []);
  
 
  return (
    <div>
      <link
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous"
      ></link>
      <br></br>
      <table class="table">
        <thead>
          <tr>
            <th></th>
            <th>Recipe</th>
            <th>Details</th>
            <th>Link to webpage</th>
          </tr>
        </thead>
        <tbody>
          {recipes.map((data, index) =>(
            <tr key={index}>
              <td>{<img src={data.thumbnail} />}</td>
              <td>{data.title}</td>
              <td>
                <Link to={`${match.url}/${data.title}/${data.ingredients}/${data.href}`}>
                  Details
                </Link>
              </td>
              <td>{<a href={data.href}> Link </a>}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <br></br>
      <Route path={`${match.path}/:title/:ingredients`}>
        <Recipe></Recipe>
      </Route>
    </div>
  );
}

function Recipe(){
  let {picture, title, ingredients, link} = useParams();
  return(
    
    <div>
      <p>Recipe: {title}</p>
      <p>Ingredients: {ingredients}</p>
      <br></br>
      {picture}
    </div>
  )
}

export default AllRecipes;

