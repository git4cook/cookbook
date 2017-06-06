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

    public Recipe getR(String recipeName){
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

//     public Recipe getSequential(){
//        String result = null;
//         if(recipe==null) {
//             result = dataAccess.getRecipeSequential(recipes);
//             currentRecipe = 0;
//         }
//         if(currentRecipe < recipes.size())
//         {
//          recipe = recipes.get(currentRecipe);
//             currentRecipe++;
//         }
//         else
//         {
//             recipes = null;
//             recipe  = null;
//             currentRecipe = 0;
//         }
//            return recipe;
//     }
//
//    public Recipe getRandom(String name)
//    {
//        recipes = dataAccess.getRecipRandom(new Recipe(name));
//        currentRecipe = 0;
//        if (currentRecipe < recipes.size())
//        {
//            recipe = recipes.get(currentRecipe);
//            currentRecipe++;
//        }
//        else
//        {
//            recipes = null;
//            recipe = null;
//            currentRecipe = 0;
//        }
//        return recipe;
//    }
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
    public boolean search(String name){
        boolean result = false;
        for(int i = 0; i<dataAccess.getList().size()&&!result;i++){
            if(dataAccess.getList().get(i).getName().toLowerCase().compareTo(name.toLowerCase())==0){
                result = true;
            }
        }
        return result;
    }
}
