import React from "react";
import { Link } from "react-router-dom";
import LatestRecipes from '../LatestRecipes';
import './HomePage.scss';

const HomePage = () => {
  return (
    <>
      <section className="section section-intro">
        <h1>Welcome to AwesomeRecipes!</h1>
        <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit. </p>
        <div className="btn-block">
          <Link to="/signUp" className="btn btn-red btn-lg">Sign Up Now</Link>
        </div>
      </section>

      <section className="section section-btnlinks">
        <div className="row">
          <div className="col-md">
            <Link to="/recipes" className="btnlink btnlink-1">
              <span className="title">Recipes</span>
              <span className="desc">Urna molestie at elementum eu facilisis sed odio morbi quis.</span>
            </Link>
          </div>
          <div className="col-md">
            <Link to="/homemade-recipes" className="btnlink btnlink-2">
              <span className="title">Homemade Recipes</span>
              <span className="desc">Urna molestie at elementum eu facilisis sed odio morbi quis.</span>
            </Link>
          </div>
          <div className="col-md">
            <Link to="/search" className="btnlink btnlink-3">
              <span className="title">Book Search</span>
              <span className="desc">Urna molestie at elementum eu facilisis sed odio morbi quis.</span>
            </Link>
          </div>
        </div>
      </section>

      <LatestRecipes />
    </>
  );
};

export default HomePage;
