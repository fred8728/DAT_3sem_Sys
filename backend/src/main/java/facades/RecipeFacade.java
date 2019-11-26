package facades;
//testtest
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
import static rest.DemoResource.getRecipeLetter;

/**
 * @author Frederik
 */
public class RecipeFacade {
  
    
    private static EntityManagerFactory emf;
    private static RecipeFacade instance;
    int threads = 8;
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
}
            

