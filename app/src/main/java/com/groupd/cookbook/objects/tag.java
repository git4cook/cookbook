package com.groupd.cookbook.objects;


import java.util.List;

/**
 * Created by wutao on 2017/6/25.
 */

public class tag  implements tagInterface{
    private List<Recipe> recipes;
    private String name;

    public tag(){
        recipes = null;
        name = null;
    }

    public tag (String name){
        name = name;
        recipes = null;
    }

    public String getTagsName(){
        return name;
    }
    public List<Recipe> getRecipeList(){
        return recipes;
    }

    public boolean Duclipate(Recipe re){
        return recipes.indexOf(re) >= 0;
    }

}
