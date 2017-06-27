package com.groupd.cookbook.business;
import java.util.ArrayList;
import java.util.List;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.persistence.DataAccess;



public class AccessRecipe implements AccessRecipeInterface {
   // private DataAccessStub dataAccess;
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
public List<Recipe>getSearchResult(){
    return dataAccess.getSearchResult();
}

    public Recipe getRecipe(String recipeName){
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
    public boolean findRecipe(String name){
        boolean result = false;
        for(int i = 0; i<dataAccess.getRecipeList().size()&&!result;i++){
            if(dataAccess.getRecipeList().get(i).getName().toLowerCase().compareTo(name.toLowerCase())==0){
                result = true;
            }
        }
        return result;
    }
   public String search(ArrayList<Recipe> input){
        String result = dataAccess.search(input);

        return result;
    }

    public List<Recipe> getRecipeList(List<Recipe> recipes)
    {

        return recipes;
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

    public Recipe insertRecipe(Recipe currentRecipe)
    {
        return currentRecipe;
    }

    public Recipe updateRecipe(Recipe currentRecipe)
  {
      return currentRecipe;
  }

    public Recipe deleteRecipe(Recipe currentRecipe)
    {
        return currentRecipe;
    }
    public void setSearchResult(ArrayList<Recipe> list){
        dataAccess.setSearchResult(list);
    }

}
