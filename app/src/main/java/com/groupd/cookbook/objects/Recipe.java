package com.groupd.cookbook.objects;

/**
 * Created by Junwei on 2017/6/2.
 */

public class Recipe
{
    private String name;
    private String direction;
    private String tags;

    public Recipe(String name)
    {
        this.name = name;
        direction = null;
    }

    public Recipe(String name, String direction)
    {
        this.name = name;
        this.direction = direction;
    }

    public Recipe(String name, String direction, String tags)
    {
        this.name = name;
        this.direction = direction;
        this.tags = tags;
    }

    public String getName()
    {
        return (name);
    }

    public String getDirection()
    {
        return (direction);
    }

    public String getTags()
    {
        return (tags);
    }

    public String toString()
    {
        return "Recipe: " +name +"\nDirection: " +direction+"\nTags: "+tags;
    }

    public boolean equals(Object object)
    {
        boolean result;
        Recipe r;

        result = false;

        if (object instanceof Recipe)
        {
            r = (Recipe) object;
            if (((r.name == null) && (name == null)) || (r.name.equals(name)))
            {
                result = true;
            }
        }
        return result;
    }
}
