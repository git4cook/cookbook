package com.groupd.cookbook.objects;

import java.util.List;

/**
 * Created by Junwei on 2017/6/2.
 */

public class Recipe implements RecipeInterface
{
    private String name;
    private String direction;
    //private String tags;
    private List<tag> tags;

    public Recipe(String name)
    {
        this.name = name;
        direction = null;
        tags = null;
    }

    public Recipe(String direction, String tags)
    {
        this.name = tags;
        this.direction = direction;
    }

    public Recipe(String name, String direction, List<tag> tags)
    {
        this.name = name;
        this.direction = direction;
        this.tags=tags;
        //addTags(tags);
    }

    public String getName()
    {
        return (name);
    }

    public String getDirection()
    {
        return (direction);
    }
    public List<tag> getRecipeTags()
    {
        return tags;
    }
    public String tagToString(){
        String tag ="";
        for(int i=0;i<tags.size();i++){
            if(tag=="")
                tag+=(tags.get(i).getTagsName());
            else
            tag+=","+(tags.get(i).getTagsName());
        }

        return tag;
    }
    /*public List<tag> getTagsList()
    {
        return tags;
    }*/
    /*public String getRecipeTags(){
        String result ="";
        for(int i =0;i<tags.size();i++){
            result+=tags.get(i).getTagsName();
            result+=",";
        }
        return result;
    }*/

    /*public String toString()
    {
        return "Recipe: " +name +"\nDirection: " +direction+"\nTags: "+tags;
    }
*/
    public void addTags(String tag){
        //tags.add(new tag(tag));

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
