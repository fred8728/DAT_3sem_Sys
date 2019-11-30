import React
    from "react";
import "../scss/AddRecipe.scss"

//PICTURE  <input type="image" src={pic} border="0" height="40" alt="img" /><tr></tr>

export default function Addrecipe() {
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
              <input type="text" className="form-control" id="recipeTitle" placeholder="Your recipe title"/>
            </div>

            <div className="row">
              <div className="col-md-6">
                <div className="form-group">
                  <label>Cooking time</label>
                  <div className="row row-narrow">
                    <div className="col-sm-3">
                      <div className="select-w">
                        <select name="" id="" className="form-control">
                          <option value="0">0</option>
                          <option value="0">1</option>
                          <option value="0">2</option>
                        </select>
                      </div>
                    </div>
                    <div className="col-sm-3">
                      <span className="txt">hours</span>
                    </div>
                    <div className="col-sm-3">
                      <div className="select-w">
                        <select name="" id="" className="form-control">
                          <option value="0">10</option>
                          <option value="0">20</option>
                          <option value="0">30</option>
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
                          <select name="" id="" className="form-control">
                            <option value="0">1</option>
                            <option value="0">2</option>
                            <option value="0">3</option>
                            <option value="0">4</option>
                            <option value="0">5</option>
                            <option value="0">6</option>
                            <option value="0">7</option>
                            <option value="0">8</option>
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
                  <input type="text" className="form-control" id="recipeTitle" placeholder="Your recipe title" value="1/4 teaspoon cayenne pepper" />
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col-sm-10">
                  <input type="text" className="form-control" id="recipeTitle" placeholder="Your recipe title"/>
                </div>
                <div className="col-sm-2">
                  <button className="btn btn-red btn-block"><i className="plus">+</i> <span>Add</span></button>
                </div>
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="description">Description</label>
              <textarea id="description" className="form-control" placeholder="Add detailed description" rows="5">
              </textarea>
            </div>

            <div className="btn-w">
              <button className="btn btn-red btn-lg">Add recipe</button>
            </div>
          </form>
        </div>
      </div>
       
    </section>
    </>
  );
    }
