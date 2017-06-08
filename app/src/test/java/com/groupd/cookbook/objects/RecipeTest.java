package com.groupd.cookbook.objects;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Glenn on 2017-06-07.
 */
public class RecipeTest {


    @Test
    public void getNameGeneral() throws Exception {

        Recipe namedRecipe = new Recipe("TestingName");
        assertEquals("TestingName",namedRecipe.getName());

    }

    @Test
    public void getNameEmpty() throws Exception {

        Recipe namedRecipe = new Recipe("");
        assertEquals("",namedRecipe.getName());

    }

    @Test
    public void getNameLong() throws Exception {

        Recipe namedRecipe = new Recipe("sa;dlfkjas;ldfjl;sadjflk;asdj;glasjdl;kgjas;ldgjasl;" +
                "dgjaskl;dgjasdgjasd;lkgjad");
        assertEquals("sa;dlfkjas;ldfjl;sadjflk;asdj;glasjdl;kgjas;ldgjasl;dgjaskl;dgjasdgjasd;" +
                "lkgjad",namedRecipe.getName());

    }

    @Test
    public void getNameSpecialCharacters() throws Exception {

        Recipe namedRecipe = new Recipe("!@#$%^&*()1234567890[]{}||.,?/><");
        assertEquals("!@#$%^&*()1234567890[]{}||.,?/><",namedRecipe.getName());

    }



    @Test
    public void getDirection() throws Exception {
        // General Case
        String testDirections= "1. boil water, 2. pinch of salt, 3 pour pasta cook 15 min";
        Recipe namedRecipe = new Recipe(testDirections);
        String returnedName = namedRecipe.getName();
        assertEquals(testDirections,returnedName);

        // Boundary cases
        testDirections =""; //empty string
        namedRecipe = new Recipe(testDirections);
        returnedName = namedRecipe.getName();
        assertEquals(testDirections,returnedName);

        // long string
        testDirections = "sa;dlfkjas;ldfjl;sadjflk;asdj;glasjdl;kgjas;ldgjasl;dgjaskl;dgjasdgjasd;lkgjad";
        namedRecipe = new Recipe(testDirections);
        returnedName = namedRecipe.getName();
        assertEquals(testDirections,returnedName);

        testDirections = "!@#$%^&*()1234567890[]{}||.,?/><"; // special characters
        namedRecipe = new Recipe(testDirections);
        returnedName = namedRecipe.getName();
        assertEquals(testDirections,returnedName);
    }


    @Test
    public void equalsSame() throws Exception {
        Recipe recipeOne = new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat",
                "breakfast,easy");
        Recipe recipeTwo = new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat",
                "breakfast,easy");
        assertTrue(recipeOne.equals(recipeTwo));
    }

    @Test
    public void equalsDifferent() throws Exception {
        Recipe recipeOne = new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat",
                "breakfast,easy");
        Recipe recipeTwo = new Recipe("mac n cheese", "1.boil pasta, 2.add chees mix, 3. wait",
                "lunch,supper,cheap,easy,cheesy");
        assertFalse(recipeOne.equals(recipeTwo));
    }

    @Test
    public void equalsEmpty() throws Exception {
        Recipe recipeOne = new Recipe("","","");
        Recipe recipeTwo = new Recipe("","","");
        assertTrue(recipeTwo.equals(recipeOne));
    }

    // Checking if recipe exists, whether it has tags or not shouldn't effect validity
    @Test
    public void equalsDiffParameters() throws Exception {
        Recipe recipeOne = new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat",
                "breakfast,easy");
        Recipe recipeTwo = new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat");

        assertTrue(recipeTwo.equals(recipeTwo));
    }

    @Test
    public void equalsWrongObject() throws Exception {
        Recipe recipeOne = new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat",
                "breakfast,easy");
        assertFalse(recipeOne.equals(this));
    }


}