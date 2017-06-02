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
    private List<Recipe> elements;
    private Recipe recipe;
    private int currentRecipe;
    public AccessRecipe (){
        dataAccess = Services.getDataAccess(Main.dbName);
        elements = null;
        recipe = null;
        currentRecipe = 0;
}

     public Recipe getSequential(){



     }
}
