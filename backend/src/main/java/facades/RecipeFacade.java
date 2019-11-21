package facades;
//testtest
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

/**
 * @author Frederik
 */
public class RecipeFacade {
  
    private static EntityManagerFactory emf;
    private static RecipeFacade instance;
    ExecutorService executorservice = Executors.newFixedThreadPool(8);
    
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
            Thread.sleep(3000);
            return "lol3";
        }
    };
    
    
      public String allFetch() throws InterruptedException, ExecutionException{
          StringBuilder sb = new StringBuilder();
          Callable<String> test1 = fetch1;
          Callable<String> test2 = fetch2;
          Callable<String> test3 = fetch3;
          Future<String> future1 = executorservice.submit(test1);
          Future<String> future2 = executorservice.submit(test2);
          Future<String> future3 = executorservice.submit(test3);
          String result1 = future1.get();
          String result2 = future2.get();
          String result3 = future3.get();
          sb = sb.append(result1);
          sb = sb.append(result2);
          sb = sb.append(result3);
          String all = sb.toString();
          //executorservice.shutdown();
         return all;
    }
}

