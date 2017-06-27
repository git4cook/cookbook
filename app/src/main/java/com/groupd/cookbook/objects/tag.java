package com.groupd.cookbook.objects;


import java.util.List;

/**
 * Created by wutao on 2017/6/25.
 */

public class tag  implements tagInterface{
    private String name;

    public tag (String name){
        name = name;
    }

    public String getTagsName(){
        return name;
    }

    /*public boolean Duclipate(Recipe re){
        return recipes.indexOf(re) >= 0;
    }*/

}
