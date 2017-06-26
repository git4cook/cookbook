package com.groupd.cookbook.business;

/**
 * Created by yinyu on 2017/6/25.
 */

import com.groupd.cookbook.objects.Recipe;
import java.util.List;


public interface AccessRecipeInterface {

    Recipe getR(String recipeName);
    boolean search(String name);
    String getRecipe(List<Recipe> recipes);
    //public Recipe getSequential();
    String insertRecipe(Recipe currentRecipe);
    String updateRecipe(Recipe currentRecipe);
    String deleteRecipe(Recipe currentRecipe);

}
