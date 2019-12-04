package rest;


import dto.CustomRecipeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import dto.CustomRecipeDTO;
import entities.CustomRecipe;
import facades.RecipeFacade;
import facades.UserFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
 * @author bud 
 */
@OpenAPIDefinition(
            info = @Info(
                    title = "recipe API",
                    version = "undefiend",
                    description = "A API that contains api with user made recipes, and API to "
                            + " a api online, this API's function is get info about recipes.",        
                    contact = @Contact( name = "Team ASEF", email = "cph-ao141@cphbusiness.dk")
            ),
          tags = {
                    @Tag(name = "recipes", description = "API related to Info about user made recipes")
              
            },
            servers = {
                    @Server(
                            description = "For Local host testing",
                            url = "http://localhost:8080/securitystarter/food"
                    ),
                    @Server(
                            description = "Server API",
                            url = "https://frederikkesimone.dk/sys/food"
                    )
                          
            }
    )
@Path("food")
public class RecipeResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static RecipeFacade facade = RecipeFacade.getRecipeFacade(EMF);
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
        return "{\"msg\":\"Hello RECIPE\"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all API recipes",
            tags = {"custom recipe"},
            responses = {
                     @ApiResponse(
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = CustomRecipe.class))),
                    @ApiResponse(responseCode = "200", description = "Returns the Requested recipes"),                       
                    @ApiResponse(responseCode = "400", description = " recipes not found")})
    @Path("recipe/all")
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all custom made recipes",
            tags = {"custom recipes"},
            responses = {
                     @ApiResponse(
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = CustomRecipe.class))),
                    @ApiResponse(responseCode = "200", description = "Returns the Requested custom recipes"),                       
                    @ApiResponse(responseCode = "400", description = "custom recipes not found")})
    @Path("recipeC/all")
    public String getAllHomemadeRecipes() {
        List<CustomRecipe> chosenRecipe = facade.getAllRecipes();
        CustomRecipeDTO custDTOClass = new CustomRecipeDTO();
        List<CustomRecipeDTO> custDTO = new ArrayList();
        for(CustomRecipe cRep : chosenRecipe){
        custDTO.add(custDTOClass.getList(cRep));
        }
        return gson.toJson(custDTO);

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "eddit custom made recipe by ID",
            tags = {"custom recipe"},
            responses = {
                     @ApiResponse(
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = CustomRecipe.class))),
                    @ApiResponse(responseCode = "200", description = "The Requested custom recipe"),                       
                    @ApiResponse(responseCode = "400", description = "custom recipe not found")})

    @Path("recipeC/edit/{id}")
    public String editRecipe(String recAsJson, @PathParam("id") int id) {
        return facade.editRecipe(recAsJson, id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a custom recipe", tags = {"custom recipe"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created recipe"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")// gotta serve
            })
    @Path("recipeC/add")
    public String addCustomRecipe(String recAsJson) {
        CustomRecipe cNew = gson.fromJson(recAsJson, CustomRecipe.class);
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cNew);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return gson.toJson(recAsJson);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary =  "Returns the Requested custom recipe", tags = {"custom recipe"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The requestd recipe"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")// gotta serve
            })
    @Path("recipeC/get/{id}")
    public String getRecipe(@PathParam("id") int id) {
        CustomRecipe chosenRecipe = facade.getRecipeById(id);
                
        //String data = chosenOne;
        System.out.println( "Recipe dATA " + chosenRecipe);
        CustomRecipeDTO recipeDTo = new CustomRecipeDTO(chosenRecipe);
        return gson.toJson(recipeDTo);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary =  "Deletes the Requested custom recipe", tags = {"custom recipe"},
            responses = {
                @ApiResponse(responseCode = "200", description = " Requestd recipe Deleted"),
                @ApiResponse(responseCode = "500", description = "Internal Server Error")// gotta serve
            })
    @Path("recipeC/{id}")
     public void deleteRecipe(@PathParam("id") int id) {
        CustomRecipe chosenRecipe = facade.getRecipeById(id);
        CustomRecipe deletedRec = chosenRecipe;
         facade.deleteCustomRecipe(chosenRecipe.getId());
                
        //String data = chosenOne;
        System.out.println( "Deleted data: " + deletedRec);
    }
}
