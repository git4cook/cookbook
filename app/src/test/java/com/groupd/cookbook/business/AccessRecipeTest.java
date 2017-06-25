package com.groupd.cookbook.business;

/**
 * Created by yinyu on 2017/6/22.
 */
import com.groupd.cookbook.objects.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import dalvik.annotation.TestTargetClass;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class AccessRecipeTest {
    private AccessRecipe test;
    private String recipeName;
    private Recipe a ;
    private Recipe b;
    private List<Recipe> testRecipes;
   @Before
    public void setUp(){
       test = new AccessRecipe();
       assertNotNull(test);
       testRecipes =null;
       a = new Recipe("pancake");
       b = new Recipe ("cake");
       testRecipes.add(a);
       testRecipes.add(b);
       recipeName = "pancake";
    }
    @After
    public void tearDown(){
        test = null;
    }

    @Test
   public void testGetRValid(){

        Recipe result = test.getR("pancake");
       assertEquals("error message",a,result);
   }
   @Test
    public void testGetRecipeValid(){
       String result = test.getRecipe(testRecipes);
       assertEquals("error message","pancake",result);
   }

    @Test
    public void testSearchNull (){
        boolean result = test.search(null);
        assertEquals("error message",false,result);
    }
    @Test
    public void testSearchValid (){
        boolean result = test.search(recipeName);
        assertEquals("error message",true,result);
    }
    @Test
    public void testInsertRecipeNull(){
        String result = test.insertRecipe(null);
        assertEquals("error message",null,result);
    }
    @Test
    public void testInsertRecipeValid(){
        String result = test.insertRecipe(a);
        assertEquals("error message","pancake",result);

    }
    @Test
    public void testUpdateRecipeNull(){
        String result = test.updateRecipe(null);
        assertEquals("error message",null,result);
    }
    @Test
    public void testUpdateRecipeValid(){
        String result = test.updateRecipe(b);
        assertEquals("error message","cake",result);

    }
    @Test
    public void testDeleteRecipeNull(){
        String result = test.deleteRecipe(null);
       assertEquals("error message",null,result);
    }
    @Test
    public void testDeleteRecipeValid(){
        String result = test.deleteRecipe(a);
        assertEquals("error message","cake",result);
    }
}
