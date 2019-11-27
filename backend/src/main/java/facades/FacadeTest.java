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
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE); //Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
            
        UserFacade userFac = UserFacade.getUserFacade(emf);
        
        userFac.deletUser("lar");
        userFac.deletUser("frede");
        userFac.createUser("lar", "bobNut@mail.dk","lolz");
        userFac.createUser("frede", "fred@nutmail.dk","lolz");
        
        System.out.println("print data " +userFac.getUser("lar").getUserName());
    }
    
}
