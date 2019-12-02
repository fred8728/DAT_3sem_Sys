// http://localhost:8080/securitystarter   https://frederikkesimone.dk/sys
const URL = "https://frederikkesimone.dk/sys";
function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}

function ApiFacade() {

  const setToken = token => {
    localStorage.setItem("jwtToken", token);
  };
  const getToken = () => {
    return localStorage.getItem("jwtToken");
  };
  const loggedIn = () => {
    const loggedIn = getToken() != null;
    return loggedIn;
  };
  const logout = () => {
    localStorage.removeItem("jwtToken");
  };

  const login = (user, pass) => {
    const options = makeOptions("POST", true, { username: user, password: pass });
    return fetch(URL + "/api/login", options)
      .then(handleHttpErrors)
      .then(res => { setToken(res.token) })
  }

  const makeOptions = (method, addToken, body) => {
    var opts = {
      method: method,
      headers: {
        "Content-type": "application/json",
        Accept: "application/json"
      }
    };
    if (addToken && loggedIn()) {
      opts.headers["x-access-token"] = getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  }
  const getRecipes = () => {
    return fetch(URL + "/api/food/recipe/all", makeOptions("GET", true)).then(handleHttpErrors)
  }

  const addRecipe = () => {
    return fetch(URL + "/api/food/recipeC/add", makeOptions("POST", true)).then(handleHttpErrors)
  }

  const updateRecipe = (id) => {
    return fetch(URL + "/api/food/recipeC/edit/" + id, makeOptions("PUT", true)).then(handleHttpErrors);
  }

  const deleteRecipe = (id) => {
    return fetch(URL + "/api/food/recipeC/" + id, makeOptions("DELETE", true)).then(handleHttpErrors);
  }

  const getCustomRecipe = (id) => {
    return fetch(URL + "recipeC/get/" + id, makeOptions("GET", true)).then(handleHttpErrors)
  }

  const getHomemadeRecipes = () => {
    return fetch(URL + "/api/food/recipeC/all", makeOptions("GET", true)).then(handleHttpErrors)
  }

  const createUser = () => {
    return fetch(URL + "/api/food/recipeC/add", makeOptions("POST", true)).then(handleHttpErrors)
  }

  return {
    login,
    logout,
    getRecipes,
    addRecipe,
    updateRecipe,
    deleteRecipe,
    getRecipe: getCustomRecipe,
    getHomemadeRecipes,
    createUser
  }

}

let returnVal = ApiFacade();
export default returnVal;
