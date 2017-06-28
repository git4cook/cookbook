//package com.groupd.cookbook.persistence;
//
//import com.groupd.cookbook.objects.Recipe;
//
//import org.junit.Test;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Glenn on 2017-06-08.
// */
//public class DataAccessStubTest {
//
//    @Test
//    public void deleteRecipe() throws Exception {
//        String testDBName = "ds";
//        DataAccessStub testDAS = new DataAccessStub(testDBName);
//        testDAS.open(testDBName);
//        boolean found = false;
//        Recipe toDelete;
//
//        ArrayList<Recipe> recipeListOriginal = new ArrayList<>();
//        ArrayList<Recipe> listPostDelete = new ArrayList<>();
//        testDAS.getRecipeSequential(recipeListOriginal);
//        toDelete = new Recipe("mac n Cheese","1.boil pasta, 2 add cheese mix, lunch");
//        testDAS.insertRecipe(toDelete);
//        testDAS.insertRecipe(new Recipe("bacon and eggs","1.crack eggs, 2. thaw bacon, 3 heat",
//                "breakfast,easy"));
//
//
//        System.out.println(recipeListOriginal);
//
//        testDAS.deleteRecipe(toDelete);
//
//        testDAS.getRecipeSequential(listPostDelete);
//        System.out.println("\n\nnew reciple list");
//        System.out.println(listPostDelete);
//        Recipe compareRecipe;
//
//        int counter = 0;
//
//        while(counter<listPostDelete.size()&&found == false)
//        {
//            compareRecipe = listPostDelete.get(counter);
//            if (compareRecipe.getName().toLowerCase().equals(toDelete.getName().toLowerCase())) {
//                found = true;
//            }
//            counter++;
//        }
//
//        assertFalse(found);
//
//    }
//}