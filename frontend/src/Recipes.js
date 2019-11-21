import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";

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


  const [recipes, setRecipes] = useState([]);

  useEffect(() => {

    apiFacade.getRecipes().then(data => {
      setRecipes(data.results)
      console.log("check data", data)
    });



  }, []);

  const search = this.state.searchTerm;
  return (
    <div>
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
        <thead>
          <tr>
            <th>Title</th>
            <th>Ingredients</th>
            <th>Thumbnail</th>
            <th>Link(?)</th>
          </tr>
        </thead>
        <tbody>

          {recipes.map((data, index) =>
            <tr key={index}>
              <td>{data.title}</td>
              <td>{data.ingredients}</td>
              <td>{<img src={data.thumbnail} />}</td>
              <td>{<a href={data.href} target="_blank"> Link </a>}</td>
            </tr>
          )}

        </tbody>
      </table>
    </div>
  );
}

export default AllRecipes;
