package facades;
//testtest

import entities.CustomRecipe;
import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
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

//    public User getUser(String username){
//    
//    //gotta get a user back from the database; 
//    }
//    public List<User> getAllUsers(){
//    
//    }
//    
    public User createUser(String username, String password, String mail) {
        EntityManager em = emf.createEntityManager();
        User newUser = new User(username, password, mail);
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            return newUser;
        } finally {
            em.close();
        }

    }

    public void deleteUser(String username) {
        EntityManager em = emf.createEntityManager();
        User userChose = em.find(User.class, username);
        User showedUser = userChose;

        try {
            em.getTransaction().begin();
            em.remove(userChose);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println(showedUser);

    }

    public User getUser(String username) {

        EntityManager em = emf.createEntityManager();
        User foundUser = em.find(User.class, username);
        return foundUser;
    }
    
    public long getAmount(){
        EntityManager em = emf.createEntityManager();
        try{
        long userCount = (long) em.createQuery("SELECT COUNT(U) FROM User U").getSingleResult();
        return  userCount;
        }finally{
            em.close();
        }
        
    }
    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("Select u from  User u", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void editUserPassword(String username, String newPassword) {

    }
    

}
