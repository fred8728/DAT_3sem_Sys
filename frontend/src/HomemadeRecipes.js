import React,{useState, useEffect} from 'react'
import facade from './apiFacade'

function CustomRecipe(){
const [customRecipe, setCustomRecipe] = useState([]);

useEffect(() =>{
facade.getHomemadeRecipes().then(data => {setCustomRecipe(data)})
})
return(
    <div>
<table>
    <thead>
        <tr>
            <th>Name: </th>
        </tr>
    </thead>
    <tbody>
        {customRecipe.map((data, index) => (
              <tr key={index}>
                <td>{data.name}</td>
              </tr>
            ))}
    <tr>
        <td></td>
    </tr>
    </tbody>
</table>
    </div>
)}

export default CustomRecipe;