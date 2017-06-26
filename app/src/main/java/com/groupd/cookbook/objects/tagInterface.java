package com.groupd.cookbook.objects;


import java.util.List;

/**
 * Created by wutao on 2017/6/25.
 */

public interface tagInterface {
    String getTagsName();
    List<Recipe> getRecipeList();
    boolean Duclipate(Recipe re);

}
