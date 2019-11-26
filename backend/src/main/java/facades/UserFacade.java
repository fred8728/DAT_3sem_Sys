package facades;
//testtest
import entities.CustomRecipe;
import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {
  
    private static EntityManagerFactory emf;
    private static UserFacade instance;
    
    private UserFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }
    
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }
    
//    public User getUser(){
//    
//    //gotta get a user back from the database;
//    
//    
//    }
//    
//    public User createUser(String username, String password, String mail){
//        EntityManager em = emf.createEntityManager();
//        User newUser = new User(username,password,mail);
//        
//        
//        
//    }
//    
//    public Custom_recipe createRecipe(User user, String nameRecipe, int portion, int cookTime, String ingredients, String description){
//        EntityManager em = emf.createEntityManager();
//        Custom_recipe recipe = new Custom_recipe(nameRecipe,portion, cookTime, ingredients,description);
//        User ourUser = user;
//        outUser.addRecipe(recipe);
//        
//        
//    
//    }
  
}
