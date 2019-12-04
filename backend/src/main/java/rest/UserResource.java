package rest;

import dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import dto.CustomRecipeDTO;
import entities.CustomRecipe;
import entities.User;
import facades.RecipeFacade;
import facades.UserFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * @author team ASEF,
 */
@OpenAPIDefinition(
        info = @Info(
                title = "User recipe API",
                version = "undefiend",
                description = "Simple API to get info about users for the recipe webpage.",
                contact = @Contact(name = "Team ASEF", email = "cph-ao141@cphbusiness.dk")
        ),
        tags = {
            @Tag(name = "user", description = "API related to Info about User")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/securitystarter/user"
            ),
            @Server(
                    description = "Server API",
                    url = "https://frederikkesimone.dk/sys/user"
            )

        }
)
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
    @Produces(MediaType.APPLICATION_JSON) // {"userName":"dasda", "email":"bob@yoho.com","userPass":"1234test"}
    @Operation(summary = "Get User info by the username",
            tags = {"user"},
//            @RequestBody( 
//                description = "the endpoints work by giveing a name value which exist in the database"
//            //name = "userName:/dasda,/ email:/bob@yoho.com,/userPass:/1234test"
//            ),
                    
            responses = {
                @ApiResponse(
                        content = @Content( mediaType = "application/json", schema = @Schema(implementation = User.class))),
                @ApiResponse(responseCode = "200", description = "The Requested User"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @Path("user/{name}")
    public String getUser(@Parameter(description ="in the url it takes a string value that returns json element ",required = true) @PathParam("name") String name) {

        User chosenOne = facadeUser.getUser(name);

        //String data = chosenOne;
        System.out.println(" dATA " + chosenOne);
        UserDTO userdto = new UserDTO(chosenOne);
        return gson.toJson(userdto);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create and add a user to a database", tags = {"user"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created user"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")// gotta serve
            })
    @Path("user/add")
    public String addUser( @Parameter(description ="takes a User JSON element as a String") String userAsJson) {
        User uNew = gson.fromJson(userAsJson, User.class);
        EntityManager em = EMF.createEntityManager();
        facadeUser.createUser(uNew.getUserName(), uNew.getEmail(), uNew.getUserPass());
        return gson.toJson(uNew);

    }
}
