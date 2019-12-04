

package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import dto.CustomRecipeDTO;
import entities.CustomRecipe;
import entities.User;
import facades.OpenMealFacade;
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
 * @author buds
 */
@OpenAPIDefinition(
            info = @Info(
                    title = "User recipe API",
                    version = "undefiend",
                    description = " API used to get info from a recipe database called openMeal for the recipes.",        
                    contact = @Contact( name = "Team ASEF", email = "cph-ao141@cphbusiness.dk")
            ),
          tags = {
                    @Tag(name = "API recipe", description = "API's used to get recipes")
              
            },
            servers = {
                    @Server(
                            description = "For Local host testing",
                            url = "http://localhost:8080/securitystarter/openmeal"
                    ),
                    @Server(
                            description = "Server API",
                            url = "https://frederikkesimone.dk/sys/openmeal"
                    )
                          
            }
    )

@Path("openmeal")
public class OpenRecipeResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static OpenMealFacade facade = OpenMealFacade.getOpenMealFacade(EMF);
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
        return "{\"msg\":\"Hello OPENMEAL\"}";
    }


//    Search meal by name
//    https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
//List all meals by first letter
//    https://www.themealdb.com/api/json/v1/1/search.php?f=a
//Lookup full meal details by id
//    https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all custom made recipes",
            tags = {"API recipes"},
            responses = {
                     @ApiResponse(
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = CustomRecipe.class))),
                    @ApiResponse(responseCode = "200", description = "Returns the Requested custom recipes"),                       
                    @ApiResponse(responseCode = "400", description = "custom recipes not found")})
    @Path("multiple")
    public String getMultiple() throws MalformedURLException, IOException, InterruptedException, ExecutionException {
        OpenMealFacade recipeFac = new OpenMealFacade();
        String all = recipeFac.allFetch();
        return all;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all custom made recipes",
            tags = {"API recipes"},
            responses = {
                     @ApiResponse(
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = CustomRecipe.class))),
                    @ApiResponse(responseCode = "200", description = "Returns the Requested custom recipes"),                       
                    @ApiResponse(responseCode = "400", description = "custom recipes not found")})
    @Path("recipe/openMeal/{letter}")
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
    @Operation(summary = "Get all custom made recipes",
            tags = {"API recipes"},
            responses = {
                     @ApiResponse(
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = CustomRecipe.class))),
                    @ApiResponse(responseCode = "200", description = "Returns the Requested custom recipes"),                       
                    @ApiResponse(responseCode = "400", description = "custom recipes not found")})
    @Path("recipe/openMeal/all")
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
}