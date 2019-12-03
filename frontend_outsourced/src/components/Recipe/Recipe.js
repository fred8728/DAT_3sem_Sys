import React, { useState, useEffect } from "react";
import { BrowserRouter, useRouteMatch, useParams, Route, Link } from "react-router-dom";
import './Recipe.scss';



function Ree() {
  let { name, time, portion, ingredients, description } = useParams();
  return (
    <div className="item-info">
                  <div className="portions">
                    <i className="icon icon-portion"></i>
                    <span>Recipe {name}</span>
                    <br></br>
                    <span>Portion: {portion}, Time: {time}</span>
                    <br></br>
                    <span>Ingredients: {ingredients}</span>
                    <br></br>
                    <span>Description: {description}</span>
                </div>
                </div>
  )
}

export default Ree;