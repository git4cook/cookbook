

package com.groupd.cookbook.test.business;


import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
//import com.groupd.cookbook.test.persistence.DataAccessStub;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by siyu on 2017/6/27.
 */

public class AccessRecipeTest extends TestCase {
    private AccessRecipe test;
    private String recipeName;
    private Recipe a ;
    private Recipe b;
    private List<Recipe> testRecipes;
    private static String dbName = Main.dbName;
    public AccessRecipeTest(String arg0)
    {
        super(arg0);
    }
    @Before
    public void setUp(){

        // test = new AccessRecipe();

        //   testRecipes =null;
        //  a = new Recipe("pancake");
        //   b = new Recipe ("cake");
        //   testRecipes.add(a);
        //   testRecipes.add(b);
        //  recipeName = "pancake";
    }
    @After
    public void tearDown(){
        test = null;
    }

    @Test
    public void testGetRecipeValid(){

        // Recipe result = test.getRecipe("pancake");
        // assertEquals("error message","pancake",result);
    }
   /* @Test
    public void testGetRecipeListValid(){
        String result = test.getRecipeList(testRecipes);
        assertEquals("error message","pancake",result);
    }

    @Test
    public void testSearchNull (){
        String result = test.search(null);
        assertEquals("error message",null,result);
    }
    /*@Test
    public void testSearchValid (){
        String result = test.search(testRecipes);
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
    }*/
}
