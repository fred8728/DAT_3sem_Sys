package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.CustomRecipe;
import entities.User;
import facades.RecipeFacade;
import facades.UserFacade;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author lam@cphbusiness.dk
 */
@Path("food")
public class DemoResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static RecipeFacade facade = RecipeFacade.getRecipeFacade(EMF);
    private static UserFacade facadeUser = UserFacade.getUserFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    //ExecutorService executorservice = Executors.newFixedThreadPool(3);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to  verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            List<User> users = em.createQuery("select user from User user").getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recipes")
    public String getRecipes(@PathParam("id") int id) throws MalformedURLException, IOException, InterruptedException, ExecutionException, ExecutionException {
        URL url = new URL("http://www.recipepuppy.com/api/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
            //jsonStr += "\n";
        }
        scan.close();
        return jsonStr;
    }

//    Search meal by name
//    https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
//List all meals by first letter
//    https://www.themealdb.com/api/json/v1/1/search.php?f=a
//Lookup full meal details by id
//    https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("multiple")
    public String getMultiple() throws MalformedURLException, IOException, InterruptedException, ExecutionException {
        RecipeFacade recipeFac = new RecipeFacade();
        String all = recipeFac.allFetch();
        return all;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recipesDB/{letter}")
    public static String getRecipeLetter(@PathParam("letter") String letter) throws MalformedURLException, IOException {
        URL url = new URL("https://www.themealdb.com/api/json/v1/1/search.php?f=" + letter);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
            //jsonStr += "\n";
        }
        scan.close();
        return jsonStr;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recipesDB/dataAll")
    public static String getAllOpenMealDB() throws IOException {
        //cached thread pool create  a cache thread pool instead of fixed if you dont know the amount of calls

        //ADD THREADS PLEASE
        StringBuilder aVal = new StringBuilder();
        StringBuilder totaldata = new StringBuilder();
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            System.out.println(alphabet);
            if (aVal.length() == 1) {
                aVal.deleteCharAt(0);
            } else {
                aVal.append(alphabet);
            }

            System.out.println(aVal);
            String vals = aVal.toString();
            totaldata.append("\n" + getRecipeLetter(vals));
            System.out.println(totaldata.toString());
        }
        String ReturnData = totaldata.toString();
        return ReturnData;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allh")
    public String getAllHomemadeRecipes() {
        //        StringBuilder str = new StringBuilder();
//        for(CustomRecipe elem : employees){
//            str.append(elem);
//        }
//        String result = str.toString();

        List<CustomRecipe> employees = facade.getAllRecipes();
        return gson.toJson(employees.toString());

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("userCustom/{name}")
    public String getUser(@PathParam("name") String name) {

        User chosenOne = facadeUser.getUser(name);
        String data = chosenOne.toString();

        return gson.toJson(data);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String editRecipe(String personAsJson, @PathParam("id") int id) {
        return facade.editRecipe(personAsJson, id);
    }
}
//test
