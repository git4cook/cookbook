package com.groupd.cookbook.test.persistence;

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.persistence.DataAccess;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinyu on 2017/6/28.
 */

public class DataAccessObjTest {
   private DataAccess test;

    @Before
    public void setUp(){

         test = new DataAccess() {
             @Override
             public void open(String string) throws myException {

             }

             @Override
             public void close() throws myException {

             }

             @Override
             public List<Recipe> getRecipeSequential() throws myException {
                 return null;
             }

             @Override
             public void insertRecipe(Recipe recipe) throws myException {

             }

             @Override
             public void updateRecipe(Recipe recipe) throws myException {

             }

             @Override
             public void deleteRecipe(Recipe recipe) throws myException {

             }

             @Override
             public Recipe getRecipe(String name) throws myException {
                 return null;
             }

             @Override
             public List<Recipe> getRecipeList() throws myException {
                 return null;
             }

             @Override
             public ArrayList<String> search(String input) throws myException {
                 return null;
             }
         };


    }

   @After
    public void tearDown(){
       test = null;
   }





}
