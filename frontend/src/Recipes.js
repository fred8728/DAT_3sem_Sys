import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";

function AllRecipes() {
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    apiFacade.getRecipes().then(data => setRecipes(data));
  }, []);

  return (
    <div>
      <table class="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Results</th>
          </tr>
        </thead>
        <tbody>
          {recipes.map((data, index) => 
          <tr key = {data.index}>
              <td>{recipes}</td>
          </tr>

          )}
        </tbody>
      </table>
    </div>
  );
}

export default AllRecipes;
