package facades;
//testtest
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomRecipeDTO;
import entities.CustomRecipe;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.TypedQuery;
import static rest.DemoResource.getRecipeLetter;

/**
 * @author Frederik
 */
public class RecipeFacade {
  
    
    private static EntityManagerFactory emf;
    private static RecipeFacade instance;
    int threads = 8;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ExecutorService executorservice = Executors.newFixedThreadPool(threads);
    
    public RecipeFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static RecipeFacade getRecipeFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<CustomRecipe> getAllRecipes(){
         EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT c from CustomRecipe c", CustomRecipe.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }    
    
    public void deleteRecipe(int id){
    }
    
    
    public CustomRecipe addRecipe(String name, int portion, int time, String ingredients, String description){
       EntityManager em = emf.createEntityManager();
        CustomRecipe cr = new CustomRecipe(name, portion, time, ingredients, description);
        try {
            em.getTransaction().begin();
            em.persist(cr);
            em.getTransaction().commit();
            return cr;
        } finally {
            em.close();
        }
    }
    
    //return
    public String editRecipe(String cRecipeJson, int id){
       CustomRecipe OriRec = getRecipeById(id);
       CustomRecipe NewRec = gson.fromJson(cRecipeJson, CustomRecipe.class);
       OriRec.setName(NewRec.getName());
       OriRec.setPortion_size(NewRec.getPortion_size());
       OriRec.setCooking_time(NewRec.getCooking_time());
       OriRec.setIngredients(NewRec.getIngredients());
       OriRec.setDescription(NewRec.getDescription());
       return gson.toJson(OriRec);
        
    }
    
    //returns a recipe
    public CustomRecipe getRecipeById(int id){
        EntityManager em = getEntityManager();
        try{
            CustomRecipe rec1 = em.find(CustomRecipe.class,id);
            return rec1;
            
        }finally{
            em.close();
            
        }
        
    }
    
    //methods
    Callable<String> fetch1 = new Callable<String>(){
        @Override
        public String call() throws Exception {
            URL url = new URL("https://swapi.co/api/people/");
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
        };
    
    Callable<String> fetch2 = new Callable<String>(){
        @Override
        public String call() throws Exception {
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
    };
    
    Callable<String> fetch3 = new Callable<String>(){
        @Override
        public String call() throws Exception {
    return "lol";
        }
    };
    
    Callable<String> fetchLetters = new Callable<String>(){
        @Override
        public String call() throws Exception {
                //cached thread pool create  a cache thread pool instead of fixed if you dont know the amount of calls
        
        //ADD THREADS PLEASE
        //StringBuilder aVal = new StringBuilder();
        StringBuilder totaldata = new StringBuilder();
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            totaldata.append("\n").append(getRecipeLetter(Character.toString(alphabet)));
            System.out.println(totaldata.toString());
        }
        String ReturnData = totaldata.toString();
        return ReturnData;
        }
    };
    
    
      public String allFetch() throws InterruptedException, ExecutionException{
          StringBuilder sb = new StringBuilder();
          Future<String> future1 = executorservice.submit(fetch1);
          Future<String> future2 = executorservice.submit(fetch2);
          Future<String> future3 = executorservice.submit(fetch3);
          Future<String> future4 = executorservice.submit(fetchLetters);
          
          //executorservice.
          
          String result1 = future1.get();
          String result2 = future2.get();
          String result3 = future3.get();
          String result4 = future4.get();
          sb = sb.append(result1);
          sb = sb.append(result2);
          sb = sb.append(result3);
          sb = sb.append(result4);
          String all = sb.toString();
          executorservice.shutdown();
         return all;
    }
      
      
      public String letterFetch() throws InterruptedException, ExecutionException{
          StringBuilder sb = new StringBuilder();
          Future<String> future4 = executorservice.submit(fetchLetters);
          String result4 = future4.get();
          sb = sb.append(result4);
          String all = sb.toString();
          executorservice.shutdown();
          return all;
      }
      
       public void createRecipe(User user, String nameRecipe, int portion, int cookTime, String ingredients, String description){
        EntityManager em = emf.createEntityManager();
       try{
        CustomRecipe recipe = new CustomRecipe(nameRecipe,portion, cookTime, ingredients,description);
        User ourUser = user;
        ourUser.addRecipe(recipe);
        em.getTransaction().begin();
        //em.persist(recipe);
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("recipe is " + recipe.toString());
        System.out.println("made by: " + user.getUserName());
       }finally{
       em.close();
       }
        
    }
}
            

