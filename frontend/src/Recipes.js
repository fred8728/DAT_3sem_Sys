import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";
import { BrowserRouter, useRouteMatch, useParams, Route, Link } from "react-router-dom";


const AllRecipes = () => {

  const [recipes, setRecipes] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  let match = useRouteMatch();
  const handleChange = e => {
    setSearchTerm(e.target.value);
    console.log(recipes)
  };


  useEffect(() => {
    const n =
      apiFacade.getRecipes().then(data => {
        setRecipes(data.results)
        console.log("check data", data.results)
      });
  }, [searchTerm]);




  return (

    <div>
      <form>
        <fieldset>
          <legend>Search for recipe</legend>
          <input
        type="text"
        placeholder="Search"
        value={searchTerm}
        onChange={handleChange}
      />
        </fieldset>
      </form>
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
          {recipes
            .filter(recipe => {
              return (
                recipe.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
                recipe.ingredients.toLowerCase().includes(searchTerm.toLowerCase())

              )
            }

            )
            .map((data, index) => (
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

function Recipe() {
  let { picture, title, ingredients, link } = useParams();
  return (

    <div>
      <p>Recipe: {title}</p>
      <p>Ingredients: {ingredients}</p>
      <br></br>
      {picture}
    </div>
  )
}

export default AllRecipes;

