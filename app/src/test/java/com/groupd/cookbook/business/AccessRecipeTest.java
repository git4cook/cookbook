package com.groupd.cookbook.business;

/**
 * Created by yinyu on 2017/6/22.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class AccessRecipeTest {
    private AccessRecipe test;
    private String recipeName;

    @Before
    public void setUp(){
         test = new AccessRecipe();
        recipeName = "pancake";
    }
    @After
    public void tearDown(){
        test = null;
    }

    @Test
   public void testGetRValid(){

       assertEquals("pancake",recipeName);
   }
  @Test
    public void testGetRNumber(){

  }

}
