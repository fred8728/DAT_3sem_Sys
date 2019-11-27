import React
    //, { useState, useEffect } 
    from "react";
import apiFacade from "./apiFacade";
import pic from "./pic/Button1.png";

export default function Addrecipe() {
    return (
        <div>
            <form action={apiFacade.addRecipe()}>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Add you very own special recipe right here</th>
                        </tr>
                    </thead>
                    <tbody>
                        <legend>Add you amazing homemade recipe image right here</legend>
                        <input type="image" src={pic} border="0" height="40" alt="img" /><tr></tr>
                        <input type="text"
                            placeholder="time"
                        /> <tr></tr>
                        <input
                            type="text"
                            placeholder="description"
                        />  <tr></tr>
                        <input
                            type="text"
                            placeholder="ingredients"
                        />   <tr></tr>
                        <input
                            type="text"
                            placeholder="Title"
                        />  <tr></tr>
                        <input
                            type="number"
                            placeholder="portions"
                        />  <tr></tr>
                        <input type="submit" value="Submit" formmethod="post"></input>
                    </tbody>
                </table>
            </form>
        </div>
    );
}

