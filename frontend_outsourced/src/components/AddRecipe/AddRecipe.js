import React, { useState, useEffect } from "react";
import './AddRecipe.scss';
import ApiFacade from "../../services/apiFacade";


//PICTURE  <input type="image" src={pic} border="0" height="40" alt="img" /><tr></tr>

export default function Addrecipe() {
  const initialValue = {
    name: "",
    portion_size: "",
    cooking_time: "",
    ingredients: "",
    description: ""
  };

  const [addRecipes, setAddRecipes] = useState(initialValue);
  console.log({ addRecipes });
  const handleChange = event => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setAddRecipes({ ...addRecipes, [name]: value });
  }
  const handleSubmit = event => {
    event.preventDefault();
    ApiFacade.addRecipe(addRecipes.name, addRecipes.portion_size, addRecipes.cooking_time, addRecipes.ingredients, addRecipes.description);
    setAddRecipes(initialValue);
    window.alert("your recipe has been added")
  };

  return (
    <>
    <section className="section section-add-recipe">
      <h1>Add recipe</h1>

        <div className="form-block">
          <div className="block-inner">
            <p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            <form action="">
              <div className="form-group">
                <label htmlFor="recipeTitle">Title</label>
                <input type="text" name="name"
                  value={addRecipes.name}
                  onChange={handleChange}
                  className="form-control" id="recipeTitle" placeholder="Your recipe title" />
              </div>

              <div className="row">
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Cooking time</label>
                    <div className="row row-narrow">
                      <div className="col-sm-3">
                        <div className="select-w">
                          <select name="cooking_time"
                            value={addRecipes.cooking_time}
                            onChange={handleChange}
                            id="cooking_time:hour" className="form-control">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                          </select>
                        </div>
                      </div>
                      <div className="col-sm-3">
                        <span className="txt">hours</span>
                      </div>
                      <div className="col-sm-3">
                        <div className="select-w">
                          <select name="cooking_time"
                            value={addRecipes.cooking_time}
                            onChange={handleChange}
                            id="cooking_time:minute" className="form-control">
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                            <option value="40">40</option>
                            <option value="50">50</option>
                          </select>
                        </div>
                      </div>
                      <div className="col-sm-3">
                        <span className="txt">minutes</span>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Serving</label>
                    <div className="row row-narrow">
                      <div className="col-sm-3">
                        <div className="input-number-w">
                          <div className="select-w">
                            <select name="portion_size" id="" className="form-control" value={addRecipes.portion_size}
                              onChange={handleChange}>
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div className="col-sm-3">
                        <span className="txt">persons</span>
                      </div>
                    </div>

                  </div>
                </div>
              </div>

              <div className="form-group">
                <label>Ingredients</label>

                <div className="row">
                  <div className="col-sm-10">
                    <div className="form-group">
                      <input type="text" className="form-control" name="ingredients"
                        value={addRecipes.ingredients}
                        onChange={handleChange}
                        id="recipeTitle" placeholder="Your recipe title" placeholder="Ingredient" />
                    </div>
                  </div>
                </div>
                
              </div>

              <div className="form-group">
                <label htmlFor="description">Description</label>
                <textarea id="description"
                  name="description"
                  value={addRecipes.description}
                  onChange={handleChange}
                  className="form-control" placeholder="Add detailed description" rows="5">
                </textarea>
              </div>

              <div className="btn-w">
                <button type="submit" value="Send" onClick={handleSubmit} className="btn btn-red btn-lg">Add recipe</button>
              </div>
            </form>
          </div>
        </div>

      </section>
      </>
  );
}