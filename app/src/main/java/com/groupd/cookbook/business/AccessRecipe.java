package com.groupd.cookbook.business;
import java.util.List;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.persistence.DataAccess;
import com.groupd.cookbook.persistence.DataAccessStub;


public class AccessRecipe implements AccessRecipeInterface {
    //private DataAccessStub dataAccess;
    private DataAccess dataAccess;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    public AccessRecipe (){
        dataAccess = Services.getDataAccess(Main.dbName);
        recipes = null;
        recipe = null;
        currentRecipe = 0;
}

    public Recipe getR(String recipeName){
        if(recipeName==null)
            return null;
        if(recipes ==null  )
        {
            recipes = dataAccess.getR(recipeName);
            currentRecipe = 0;
        }
            if(currentRecipe <recipes.size()){
                recipe = recipes.get(currentRecipe);
                currentRecipe++;
            }
          else{
                recipes = null;
                recipe = null;
                currentRecipe = 0;
            }
            return recipe;
    }
    public boolean search(String name){
        boolean result = false;
        for(int i = 0; i<dataAccess.getRecipeList().size()&&!result;i++){
            if(dataAccess.getRecipeList().get(i).getName().toLowerCase().compareTo(name.toLowerCase())==0){
                result = true;
            }
        }
        return result;
    }

    public String getRecipe(List<Recipe> recipes)
    {
        recipes.clear();
        return dataAccess.getRecipeSequential(recipes);
    }
   /* public Recipe getSequential(){
         if(recipe==null) {
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
     }*/

    public String insertRecipe(Recipe currentRecipe)
    {
        return dataAccess.insertRecipe(currentRecipe);
    }

    public String updateRecipe(Recipe currentRecipe)
  {
      return dataAccess.updateRecipe(currentRecipe);
  }

    public String deleteRecipe(Recipe currentRecipe)
    {
        return dataAccess.deleteRecipe(currentRecipe);
    }

}
