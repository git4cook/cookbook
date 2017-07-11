package com.groupd.cookbook.objects;

import java.util.List;

/**
 * Created by Junwei on 2017/6/2.
 */

public class Recipe implements RecipeInterface
{
    private String name;
    //private String direction;
    private List<step> steps;
    //private String tags;
    private List<tag> tags;
    private String need;


/*
    public Recipe(String name, String direction, List<tag> tags)
    {
        this.name = name;
        this.direction = direction;
        this.tags=tags;
        //addTags(tags);
    }
    */

    public Recipe(String name, List<step> steps, List<tag> tags,String need)
    {
        this.name = name;
        this.steps = steps;
        this.tags=tags;
        this.need = need;
        //addTags(tags);
    }

    public String getName()
    {
        return (name);
    }
    public String getNeed()
    {
        return (need);
    }

    public List<step> getRecipeSteps()
    {
        return steps;
    }

    public String stepToString(){
        String step ="";
        for(int i=0;i<steps.size();i++){
            if(step=="")
                step+=(steps.get(i).getStepsName());
            else
                step+=","+(steps.get(i).getStepsName());
        }

        return step;
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
