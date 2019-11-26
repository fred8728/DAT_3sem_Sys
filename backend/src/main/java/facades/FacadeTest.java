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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
            
        UserFacade userFac = UserFacade.getUserFacade(emf);
        
        userFac.createUser("bob", "lolz", "bobNut@mail.dk");
        
    }
    
}
