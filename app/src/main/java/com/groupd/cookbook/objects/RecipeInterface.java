package com.groupd.cookbook.objects;

import java.util.List;

/**
 * Created by wutao on 2017/6/25.
 */

public interface RecipeInterface {

    String getName();

    String getDirection();

    List<tag> getTags();

    boolean equals(Object object);

}
