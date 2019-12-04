package facades;

import entities.CustomRecipe;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class RecipeFacadeTest {

    private static EntityManagerFactory emf;
    private static RecipeFacade facade;
    private CustomRecipe recipe1 = new CustomRecipe("basic pasta", 4, 2, "pasta, tomato sauce, salt, cheese", "cook pasta, add tomato sauce and cheese.");
    private CustomRecipe recipe2 = new CustomRecipe("chicken", 4, 2, "chicken, butter sauce, salt", "cook chicken with butter sauce.");
    private CustomRecipe recipe3 = new CustomRecipe("salat", 3, 1, "salat, tomato , salt sauce, cucumber", "cut the veggies and mix them all in bowl");
    private CustomRecipe recipe4 = new CustomRecipe("nuts mix", 4, 2, "nuts, lots of nuts", "just mix nuts");
    

    public RecipeFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
       /* emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade =  RecipeFacade.getRecipeFacade(emf);*/
       EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
    } 

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = RecipeFacade.getRecipeFacade(emf); // GOTTA GET THIS RIGTH 
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {      
            em.getTransaction().begin();
            em.createQuery("delete from CustomRecipe").executeUpdate();
            /*
            //old data
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.createNamedQuery("CustomRecipe.deleteAll").executeUpdate();
            //em.persist(new RenameMe("Some txt", "More text"));
            //em.persist(new RenameMe("aaa", "bbb"));
            */
            em.persist(recipe1);
            em.persist(recipe2);
            em.persist(recipe3);
            em.persist(recipe4);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void countUserTest() {
        assertEquals(4, facade.getAmount(), "Expects a amout of users added in setup");
    }

    
//    @Test
//    public void findUserTest() {
//        CustomRecipe recipe = facade.getRecipeByName("basic pasta");  //SHIT METHOD
//       // System.out.println("data2 you " + recipe.getName());
//        assertEquals(recipe.getName(), "basic pasta");
//    }
    
  @Test
    public void checkListAreEqualTest(){
        List<CustomRecipe> receipes = facade.getAlleRecipes();
        List <CustomRecipe> recip = new ArrayList();
        recip.add(recipe1);
        recip.add(recipe2);
        recip.add(recipe3);
        recip.add(recipe4);
        
        assertNotNull(receipes);
        assertNotNull(recip);
        assertEquals(receipes.size(), recip.size());
    }
    
    /*
    // -- code to change and use for user
    // also use for other code
    */
    /**
     * This method check if it is possible to get a members information by only typing their name
     */
    /*
    @Test
    public void getUserByNameTest(){
        //List <GroupMember> member = facade.getMemberByName(m3.getName());
        //CustomRecipe recipe = facade.getRecipeByName("salat");
        CustomRecipe recipe = facade.getRecipeById(2);
        assertNotNull(recipe);
        assertEquals(recipe.getName().toLowerCase(), "salat");
        assertEquals(recipe.getCooking_time(), 1);
    }
    */
    
    @Test
    public void addRecipeTest(){
        
        CustomRecipe recipeTest = facade.createCustomRecipe("nuts mix", 4, 2, "nuts, lots of nuts", "just mix nuts");
        assertEquals(5, facade.getAmount());
        
        
    }
   

}
