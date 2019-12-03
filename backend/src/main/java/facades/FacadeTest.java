/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utils.EMF_Creator;

/**
 *
 * @author ahmed
 */
public class FacadeTest {
    
    private static UserFacade instance;
    private static EntityManagerFactory emf;
     private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE); //Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
            
        UserFacade userFac = UserFacade.getUserFacade(emf);
        RecipeFacade recFac = RecipeFacade.getRecipeFacade(emf);
     
        
//        userFac.createUser("lar", "bobNut@mail.dk","lolz");
//        userFac.createUser("frede", "fred@nutmail.dk","lolz");
//        
//        System.out.println("print data " +userFac.getUser("admin").getUserName());
//        
//        recFac.addRecipe("pasta", 3, 1, "pata, oil, tomato, cheses", "cook pasta, add oil toamto and chese when cooked done");
//        recFac.addRecipe("pasta2 with fish", 3, 1, "pata, oil, tomato, cheses, can tuna", "cook pasta, add oil toamto and chese when cooked done and can tuna");
//        
        System.out.println(recFac.getRecipeByName("pasta"));
//        System.out.println("get list size" + recFac.getAllRecipes().size());
//        RecipeFacade rf = RecipeFacade.getRecipeFacade(emf);
//        rf.addRecipe("Burrito", 3, 30, "Pandekager, salsa, salat", "Snart med at varme pandekagerne i ovnen");
//        

        //System.out.println(userFac.getAmount());
    }
    
}
