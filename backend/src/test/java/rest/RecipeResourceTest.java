package rest;

import entities.CustomRecipe;
import entities.User;
import entities.Role;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

//@Disabled
public class RecipeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final String TEST_DB = "jdbc:mysql://localhost:3307/startcode_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    

    CustomRecipe cr = new CustomRecipe("Spaghetti kødsovs", 4, 40, "Oksekød, tomatsovs, spaghetti, parmazan", "Start med at putte oksekødet i gryden");
    CustomRecipe cr1 = new CustomRecipe("Spaghetti Carbonare", 4, 40, "Spaghetti, ost, Fløde, Æg", "Kog Spaghetti");
    
    
    
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
    em.getTransaction().begin();
    //em.createQuery("DELETE FROM CUSTOMRECIPE").executeUpdate();
    em.persist(cr);
    em.persist(cr1);
    em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/food").then().statusCode(200);
    }
    
    @Test
    public void testGetAllRecipes() {
        given()
                .contentType("application/json")
                .get("/food/recipe/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("title", equalTo("Recipe Puppy"));
        //System.out.println(m2);
    }
    
    @Test
    public void testGetAllCustomRecipes() {
        given()
                .contentType("application/json")
                .get("/food/recipeC/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", hasItems("Spaghetti Carbonare", "Spaghetti kødsovs"));
    }
    
//        @Test
//    public void testGetCustomRecipes() {
//        given()
//                .contentType("application/json")
//                .get("/food/recipeC/get/2").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("description", equalTo("Start med at putte oksekødet i gryden"));
//    }
    
         @Test
    public void testPostCustomRecipes() {
            String postlol = "{\n" +
"    \"name\": \"Chili con carne\",\n" +
"    \"portion_size\": 6,\n" +
"    \"cooking_time\": 60,\n" +
"    \"ingredients\": \"Chili, bønner, kødsovs\",\n" +
"    \"description\": \"Put det hele i en gryde.\"\n" +
"  }";
            RecipeResource posttest = new RecipeResource();
            posttest.addCustomRecipe(postlol);
        given()
                .contentType("application/json")
                .get("/food/recipeC/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", hasItems("Spaghetti Carbonare", "Spaghetti kødsovs", "Chili con carne"));
    }
    

    
    
}
