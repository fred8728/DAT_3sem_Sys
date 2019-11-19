package facades;
//testtest
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Frederik
 */
public class RecipeFacade {
  
    private static EntityManagerFactory emf;
    private static RecipeFacade instance;
    ExecutorService executorservice = Executors.newFixedThreadPool(8);
    
    private RecipeFacade(){}
    
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
            //do stuff
            return "lol1";
        }
    };
    
    Callable<String> fetch2 = new Callable<String>(){
        @Override
        public String call() throws Exception {
            //do stuff
            return "lol2";
        }
    };
    
    Callable<String> fetch3 = new Callable<String>(){
        @Override
        public String call() throws Exception {
            //do stuff
            return "lol3";
        }
    };
    

}

