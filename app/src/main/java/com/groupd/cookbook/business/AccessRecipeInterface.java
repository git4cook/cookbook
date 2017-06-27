package com.groupd.cookbook.business;

/**
 * Created by yinyu on 2017/6/25.
 */

import com.groupd.cookbook.objects.Recipe;

import java.util.ArrayList;
import java.util.List;


public interface AccessRecipeInterface {

    Recipe getRecipe(String recipeName);
    boolean findRecipe(String name);
    String getRecipeList(List<Recipe> recipes);
    //public Recipe getSequential();
    String insertRecipe(Recipe currentRecipe);
    String updateRecipe(Recipe currentRecipe);
    String deleteRecipe(Recipe currentRecipe);
    String search(ArrayList<Recipe> input);

    void setSearchResult(ArrayList<Recipe> list);
}
