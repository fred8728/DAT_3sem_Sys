import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";
import { BrowserRouter, useRouteMatch, useParams, Route, Link } from "react-router-dom";

<<<<<<< HEAD
const people = [
  "Siri",
  "Alexa",
  "Google",
  "Facebook",
  "Twitter",
  "Linkedin",
  "Sinkedin"
];


const AllRecipes = (props) => {

  const [searchTerm, setSearchTerm] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const handleChange = e => {
    setSearchTerm(search : e.target.value);
  };
  useEffect(() => {
    const results = people.filter(person =>
      person.toLowerCase().includes(searchTerm)
    );
    setSearchResults(results);
  }, [searchTerm]);


=======
const AllRecipes = () =>{
>>>>>>> 240229eae1dde2098c240c0a34b10a301ceb7aa0
  const [recipes, setRecipes] = useState([]);
  let match = useRouteMatch();

  useEffect(() => {
<<<<<<< HEAD

    apiFacade.getRecipes().then(data => {
      setRecipes(data.results)
      console.log("check data", data)
    });



=======
    
      apiFacade.getRecipes().then(data => {setRecipes(data.results)
      console.log("check data",data)});
>>>>>>> 240229eae1dde2098c240c0a34b10a301ceb7aa0
  }, []);

  const search = this.state.searchTerm;
  return (
    <div>
<<<<<<< HEAD
      <input
        type="text"
        placeholder="Search"
        value={searchTerm}
        onChange={handleChange}
      />
      <ul>
        {searchResults.map(item => (
          <li>{item}</li>
        ))}
      </ul>
      <table>
=======
      <link
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous"
      ></link>
      <br></br>
      <table class="table">
>>>>>>> 240229eae1dde2098c240c0a34b10a301ceb7aa0
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

