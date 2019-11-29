package facades;
//testtest

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import static rest.OpenRecipeResource.getRecipeLetter;

/**
 * @author Frederik
 */
public class RecipeFacade {

    private static EntityManagerFactory emf;
    private static RecipeFacade instance;
    int threads = 8;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ExecutorService executorservice = Executors.newFixedThreadPool(threads);

    public RecipeFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static RecipeFacade getRecipeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<CustomRecipe> getAllRecipes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT c from CustomRecipe c", CustomRecipe.class);
            return query.getResultList();
        } finally {
            em.close();
        }

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
    public String editRecipe(String cRecipeJson, int id) {
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
    public CustomRecipe getRecipeById(int id) {
        EntityManager em = getEntityManager();
        try {
            CustomRecipe rec1 = em.find(CustomRecipe.class, id);
            return rec1;

        } finally {
            em.close();
            
        }  
    }
    public CustomRecipe getRecipeByName(String name){
        EntityManager em = getEntityManager();
        try{
            CustomRecipe rec1 = em.find(CustomRecipe.class,name);
            return rec1;
            
        }finally{
            em.close();
            
        }
    }
    public void deleteCustomRecipe(int id) {
        EntityManager em = emf.createEntityManager();
        CustomRecipe recipeCust = em.find(CustomRecipe.class,id);
        CustomRecipe deletedRecipe = recipeCust;
        
        try{
        em.getTransaction().begin();
        em.remove(recipeCust);
        em.getTransaction().commit();
        }finally{
        em.close();
        }
        System.out.println(deletedRecipe);
        

    }

   
}
