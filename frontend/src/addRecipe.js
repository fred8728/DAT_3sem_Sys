import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";


export default function Addrecipe() {
    return (
        <div>
            <form>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Add you very own special recipe right here</th>
                        </tr>
                    </thead>
                    <tbody>
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
                        <input type="submit" value="Submit"></input>
                    </tbody>
                </table>
            </form>
        </div>
    );
}

