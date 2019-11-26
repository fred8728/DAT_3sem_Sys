import React, {useState} from 'react'

function Profile(){
const [user, setUser] = useState([]);


return (
    <div>
        <h1>Profile information</h1>
        <br></br>
        
        <p>Username: </p>
        <p>Email: </p>
        <br></br>
        <h3>Own recipes</h3>
        
        <button>Add recipe</button>
    </div>
)
}

export default Profile;