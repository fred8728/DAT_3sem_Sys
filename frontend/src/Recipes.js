import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";

const AllRecipes = () =>{
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
      apiFacade.getRecipes()
      .then(data => setRecipes(data));
  }, []);

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Title</th>
          </tr>
        </thead>
        <tbody>
          {recipes.map((data, index) => 
              <tr key={index}>
                <td>{data.title}</td>
              </tr>
            )
          }
        </tbody>
      </table>
    </div>
  );
}

export default AllRecipes;
