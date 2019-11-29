/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.CustomRecipe;
import entities.User;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static rest.OpenRecipeResource.getRecipeLetter;

/**
 *
 * @author fskn
 */
public class OpenMealFacade {
     private static EntityManagerFactory emf;
    private static OpenMealFacade instance;
    int threads = 8;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ExecutorService executorservice = Executors.newFixedThreadPool(threads);

    public OpenMealFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static OpenMealFacade getOpenMealFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OpenMealFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     //methods
    Callable<String> fetch1 = new Callable<String>() {
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

    Callable<String> fetch2 = new Callable<String>() {
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

    Callable<String> fetch3 = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return "lol";
        }
    };

    Callable<String> fetchLetters = new Callable<String>() {
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

    public String allFetch() throws InterruptedException, ExecutionException {
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

    public String letterFetch() throws InterruptedException, ExecutionException {
        StringBuilder sb = new StringBuilder();
        Future<String> future4 = executorservice.submit(fetchLetters);
        String result4 = future4.get();
        sb = sb.append(result4);
        String all = sb.toString();
        executorservice.shutdown();
        return all;
    }

    public void createRecipe(User user, String nameRecipe, int portion, int cookTime, String ingredients, String description) {
        EntityManager em = emf.createEntityManager();
        try {
            CustomRecipe recipe = new CustomRecipe(nameRecipe, portion, cookTime, ingredients, description);
            User ourUser = user;
            ourUser.addRecipe(recipe);
            em.getTransaction().begin();
            //em.persist(recipe);
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("recipe is " + recipe.toString());
            System.out.println("made by: " + user.getUserName());
        } finally {
            em.close();
        }

    }
}
