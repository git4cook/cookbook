package com.groupd.cookbook.business;

/**
 * Created by yinyu on 2017/6/25.
 */

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;

import java.util.ArrayList;
import java.util.List;


public interface AccessRecipeInterface {

    Recipe getRecipe(String recipeName) throws myException;
    boolean findRecipe(String name) throws myException;
    List<Recipe> getRecipeList() throws myException;
    //public Recipe getSequential();
    void insertRecipe(Recipe currentRecipe) throws myException;
    void updateRecipe(Recipe currentRecipe) throws myException;
    void deleteRecipe(Recipe currentRecipe) throws myException;
    boolean search(String input) throws myException;

}
