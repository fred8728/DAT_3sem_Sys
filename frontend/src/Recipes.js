import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";

const AllRecipes = (props) => {
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    apiFacade.getRecipes().then(data => {
      console.log("tester", data)
      setRecipes(data.results)
    });


  }, []);


  return (
    <div>
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
              <td>{<a href={data.href}> Link </a>}</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default AllRecipes;
