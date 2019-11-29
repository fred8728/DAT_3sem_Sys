

package rest;

import dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import dto.CustomRecipeDTO;
import entities.CustomRecipe;
import entities.User;
import facades.RecipeFacade;
import facades.UserFacade;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author lam@cphbusiness.dk
 */
@Path("user")
public class UserResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static UserFacade facadeUser = UserFacade.getUserFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    int nextId = 3;
    //ExecutorService executorservice = Executors.newFixedThreadPool(3);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello USERRESOURCE\"}";
    }
//push
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{name}")
    public String getUser(@PathParam("name") String name) {

        User chosenOne = facadeUser.getUser(name);

        //String data = chosenOne;
        System.out.println( "XX dATA " + chosenOne);
        UserDTO userdto = new UserDTO(chosenOne);
        return gson.toJson(userdto);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/add")
    public String addUser(String userAsJson) {
        User uNew = gson.fromJson(userAsJson, User.class);
        EntityManager em = EMF.createEntityManager();
        facadeUser.createUser(uNew.getUserName(), uNew.getEmail(), uNew.getUserPass());  
        return gson.toJson(uNew);

    }
}
