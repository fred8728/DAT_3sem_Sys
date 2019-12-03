import React from "react";
import { Link } from "react-router-dom";
import './LatestRecipes.scss';

import tmpImg from "../../assets/images/tmp/recipe-lg.jpg"; // temp

const LatestRecipes = () => {
  return (
    <>
      <section className="section section-latest-recipes">
        <div className="section-heading">
          <h2>Latest Recipes</h2>
          <div className="all"><Link to="/recipes">See all &raquo;</Link></div>
        </div>
        <div className="section-body">
          <div className="recipe-blocks">
            <div className="row">
              <div className="col-md-4">
                <div className="recipe-block">
                  <Link to="">
                    <span className="recipe-block-img"><img src={tmpImg}/></span>
                    <span className="recipe-block-title">Ham, Asparagus, and Goat Cheese Strata with some more ingredients</span>
                  </Link>
                </div>
              </div>
              <div className="col-md-4">
                <div className="recipe-block">
                  <Link to="">
                    <span className="recipe-block-img"><img src={tmpImg}/></span>
                    <span className="recipe-block-title">Cheesy Sausage Lasagna Soup</span>
                  </Link>
                </div>
              </div>
              <div className="col-md-4">
                <div className="recipe-block">
                  <Link to="">
                    <span className="recipe-block-img"><img src={tmpImg}/></span>
                    <span className="recipe-block-title">Apple and Sausage Cornbread Stuffing</span>
                  </Link>
                </div>
              </div>
            </div>
            
          </div>
        </div>
        
      </section>
    </>
  );
}

export default LatestRecipes;
