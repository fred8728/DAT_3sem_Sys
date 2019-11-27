import React, {useState, useEffect} from 'react'

function CreateUser(props) {
const facade = props.apiFacade;
const [user, setUser] = useState([props.newUser]);
const initialState = {username: "", email: "" , password: ""}
//const newUser = facade.newUser;
//const { createUser} = facade;

const handleChange = event =>{
    const target = event.target;
    const id = target.id;
    const value = target.value;
    setUser({...user,[id]: value})
}

const handleSubmit = event =>{
    event.preventDefault();
    //createUser(user);
    setUser({...initialState})
}

/* useEffect (() => 
   setUser({...newUser}), [newUser])
*/
    return(
        <div>
      <form onChange={handleChange} onSubmit={handleSubmit}>
        <h1>Create User</h1>
        <div>
          <label>Username:</label>
          <div>
            <input
              id="username"
              value={user.username}
              placeholder="username"
              onChange={handleChange}
            />
          </div>
        </div>
        <div>
          <label>Email:</label>
          <div>
            <input
              id="email"
              value={user.email}
              placeholder="email"
              onChange={handleChange}
            />
          </div>
        </div>
        <div>
          <input type="submit" value="Save" onSubmit={handleSubmit} />
        </div>
      </form>
      <p>{JSON.stringify(user)}</p>
        </div>
    )
}

export default CreateUser;