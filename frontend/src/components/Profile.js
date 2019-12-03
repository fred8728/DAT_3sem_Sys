import React from "react";
import { NavLink } from 'react-router-dom';
import {  Link } from "react-router-dom";
import '../scss/Recipes.scss';
import "../scss/Profile.scss"

import tmpImg from "../assets/images/tmp/userpic.jpg"; // temp

function Profile  (props) {
  const {user} = props;
    return (
      <section className="section section-profile">
        <div className="section-heading">
          <h1>Your profile</h1>
        </div>
        <div className="section-body">
          <div className="profile-l">
            <div className="profile-userpic">
              <img src={tmpImg} alt=""/>
            </div>
            <div className="profile-info">
              <div className="profile-name">Awesome Chef Master</div>
              <div className="profile-registered">
                <span className="label">Registered:</span> <span className="val">12.09.2019</span>
              </div>
              <div className="profile-edit">
                <a href=""><i className="icon icon-edit"></i> <span>Edit profile</span></a>
              </div>
  
              <div className="btn-w">
                <NavLink to="/add-recipe" className="btn btn-red">Add recipe</NavLink>
              </div>
            </div>
          </div>
          <div className="profile-r">
            <section className="section section-recipes">
              <h2>Recipes you added</h2>
              
              
              <div className="recipe-list">
                <div className="item">
                  <div className="item-img">{<img src="" alt="img"/>}</div>
                  <div className="item-r">
                    <div className="item-c">
                      <div className="item-title">Recipe Title</div>
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
                      <Link className="btn btn-green btn-sm btn-block" to="">
                        Details
                      </Link>
                      <a className="btn btn-grey btn-sm btn-block" href=""> Link </a>
                    </div>
                  </div>
                </div>
  
              </div>
            </section>
          </div>
        </div>
      </section>
    )
  }

export default Profile;