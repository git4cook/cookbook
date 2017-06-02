package com.groupd.cookbook.business;

/**
 * Created by siyu on 2017/6/2.
 */
import java.util.List;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.persistence.DataAccessStub;


public class AccessRecipe {
    private DataAccessStub dataAccess;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    public AccessRecipe (){
        dataAccess = Services.getDataAccess(Main.dbName);
        recipes = null;
        recipe = null;
        currentRecipe = 0;
}

    public String getRecipe(List<Recipe> recipes)
    {
        recipes.clear();
        return dataAccess.getRecipeSequential(recipes);
    }

     public Recipe getSequential(){
        String result = null;
         if(recipe==null) {
             result = dataAccess.getRecipeSequential(recipes);
             currentRecipe = 0;
         }
         if(currentRecipe < recipes.size())
         {
          recipe = recipes.get(currentRecipe);
             currentRecipe++;
         }
         else
         {
             recipes = null;
             recipe  = null;
             currentRecipe = 0;
         }
            return recipe;
     }
}
