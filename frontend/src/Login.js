import React, {useState} from 'react'

export default function Login (props) {
    const [loggedIn, setLoggedIn] = useState();

    const submitLogin = (evt) => {
      evt.preventDefault();
      props.login(loggedIn.username, loggedIn.password);
    }
    const onChange = (evt) => {
      setLoggedIn({...loggedIn, [evt.target.id]: evt.target.value})
    }
      return (
        <div>
          <h2>Please enter your information</h2>
          <form onSubmit={submitLogin} onChange={onChange} >
            <input placeholder="User Name" id="username" />
            <input placeholder="Password" id="password" />
            <button>Sign in</button>
          </form>
        </div>
        
      )
   }