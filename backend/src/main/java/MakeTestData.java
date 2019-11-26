
import facades.RecipeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fskn
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
   
        RecipeFacade rs = RecipeFacade.getRecipeFacade(emf);
        
        System.out.println(rs.getAllRecipes().toString());
    }
}
