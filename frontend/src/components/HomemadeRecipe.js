import React,{useState, useEffect} from 'react'
import facade from './apiFacade'
import { Link, useRouteMatch
} from "react-router-dom";
import SearchForm from './SearchForm'

function CustomRecipe(){
const [customRecipe, setCustomRecipe] = useState([]);
let match = useRouteMatch();

useEffect(() =>{
facade.getHomemadeRecipes().then(data => { setCustomRecipe(data)})
},[])
console.log("check data", customRecipe)
return(
    <>
    <section className="section section-recipes">
      <h1>Recipes</h1>
      <div className="recipe-list">
          {customRecipe
            .map((data, index) => (
              <div className="item" key={index}>
            <div className="item-r">
              <div className="item-c">
              <div className="item-title">{data.name}</div>
                <div className="item-info">
                  <div className="portions">
                    <i className="icon icon-portion"></i>
                    <span>4 portions</span>
                  </div>
                  <div className="time">
                    <i className="icon icon-time"></i>
                    <span>1 h 30 m</span>
                  </div>
                </div>
              </div>
              <div className="item-links">
                <Link className="btn btn-green btn-sm btn-block" to={`${match.url}/${data.title}/${data.ingredients}/${data.href}`}>
                  Details
                </Link>
                <a className="btn btn-grey btn-sm btn-block" href={data.href}> Link </a>
              </div>
            </div>
            
          </div>
        ))}
      </div>
    </section>
    </>
  );
}

export default CustomRecipe;