package com.groupd.cookbook.business;

/**
 * Created by yinyu on 2017/6/25.
 */

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.persistence.myException;

import java.util.ArrayList;
import java.util.List;


public interface AccessRecipeInterface {

    Recipe getRecipe(String recipeName);
    boolean findRecipe(String name) throws myException;
    List<Recipe> getRecipeList(List<Recipe> recipes);
    //public Recipe getSequential();
    Recipe insertRecipe(Recipe currentRecipe);
    Recipe updateRecipe(Recipe currentRecipe);
    Recipe deleteRecipe(Recipe currentRecipe);
    String search(ArrayList<Recipe> input);

    void setSearchResult(ArrayList<Recipe> list);
}
