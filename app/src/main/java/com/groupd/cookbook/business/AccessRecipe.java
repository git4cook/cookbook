package com.groupd.cookbook.business;
import java.util.ArrayList;
import java.util.List;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.persistence.DataAccess;



public class AccessRecipe implements AccessRecipeInterface {
   // private DataAccessStub dataAccess;
    private DataAccess dataAccess;

    public AccessRecipe (){
        dataAccess = Services.getDataAccess(Main.dbName);
}


    public Recipe getRecipe(String recipeName) throws myException {
        return dataAccess.getRecipe(recipeName);
    }
    public boolean findRecipe(String name) throws myException {
        boolean result = false;
        for(int i = 0; i<dataAccess.getRecipeList().size()&&!result;i++){
            if(dataAccess.getRecipeList().get(i).getName().toLowerCase().compareTo(name.toLowerCase())==0){
                result = true;
            }
        }
        return result;
    }
   public ArrayList<String> search(String input) throws myException {


        return dataAccess.search(input);
    }

    public ArrayList<Recipe> getRecipeList() throws myException {

        return (ArrayList<Recipe>) dataAccess.getRecipeSequential();
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

    public void insertRecipe(Recipe currentRecipe) throws myException {
        dataAccess.insertRecipe(currentRecipe);
    }

    public void updateRecipe(Recipe currentRecipe) throws myException {
    dataAccess.updateRecipe(currentRecipe);
  }

    public void deleteRecipe(String currentRecipe) throws myException {
        dataAccess.deleteRecipe(currentRecipe);
    }

}
