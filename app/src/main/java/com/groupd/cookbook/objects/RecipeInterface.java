package com.groupd.cookbook.objects;

import java.util.List;

/**
 * Created by wutao on 2017/6/25.
 */

public interface RecipeInterface {

    String getName();

    //String getDirection();
    String stepToString();
    List<step> getRecipeSteps();
    String tagToString();
    //List<tag> getTagsList();
    List<tag> getRecipeTags();
    boolean equals(Object object);

}
