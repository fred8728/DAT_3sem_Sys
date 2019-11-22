import React, {useState,  Component } from "react"
import facade from "./apiFacade";

function LogIn(props){
  const [state, setState] = useState();

  const login =(event) =>{
    event.preventDefault();
    props.login(state.username, state.password);
  }
  const onChange = (event) =>{
    setState({...state, [event.target.id]: event.target.value})
  }

   return (
     <div>
       <h2>Login</h2>
       <form onSubmit={login} onChange={onChange} >
         <input placeholder="User Name" id="username" />
         <input placeholder="Password" id="password" />
         <button>Login</button>
       </form>
     </div>
   )
 }

function LoggedIn(){

  return (
    <div>
      <h2>Data Received from server</h2>
    </div>
  )
}


class App extends Component {
 constructor(props) {
   super(props);
   this.state = { loggedIn: false }
 }

 logout = () => {
  facade.logout();
  this.setState({ loggedIn: false });
 } 

 login = (user, pass) => {
  facade.login(user,pass)
  .then(res =>this.setState({ loggedIn: true }));
 } 
 
 render() {
   return (
     <div>
       {!this.state.loggedIn ? (<LogIn login={this.login} />) :
         ( <div>
             <LoggedIn/>
             <button onClick={this.logout}>Logout</button>
           </div>)}
     </div>
   )
 }
}
export default App;