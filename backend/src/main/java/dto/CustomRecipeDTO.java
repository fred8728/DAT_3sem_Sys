/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.CustomRecipe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruger
 */
public class CustomRecipeDTO {
    
    private Integer id;
    private String name;
    private int portion_size;
    private int cooking_time;
    private String ingredients;
    private String description;
    
    public CustomRecipeDTO(){}
    
    public CustomRecipeDTO(CustomRecipe cRep){
        this.id = cRep.getId();
        this.name = cRep.getName();
        this.portion_size = cRep.getPortion_size();
        this.cooking_time = cRep.getCooking_time();
        this.ingredients = cRep.getIngredients();
        this.description = cRep.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPortion_size() {
        return portion_size;
    }

    public void setPortion_size(int portion_size) {
        this.portion_size = portion_size;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CustomRecipeDTO> getList(CustomRecipe cRest){ //List<CustomRecipe> list1,
        List<CustomRecipeDTO> cList = new ArrayList();
        
        CustomRecipeDTO data = new CustomRecipeDTO(cRest);
        cList.add(data);
        return cList;
    }
}
