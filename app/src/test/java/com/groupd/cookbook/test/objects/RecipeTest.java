package com.groupd.cookbook.test.objects;

import android.support.annotation.NonNull;

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.tag;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

/**
 * Created by Glenn on 2017-06-07.
 */
public class RecipeTest {
    private Recipe test;
    private List<tag> tag;


    @Before
     public void setUp(){
        tag = new List<com.groupd.cookbook.objects.tag>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<tag> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(tag tag) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends tag> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends tag> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public tag get(int index) {
                return null;
            }

            @Override
            public tag set(int index, tag element) {
                return null;
            }

            @Override
            public void add(int index, tag element) {

            }

            @Override
            public tag remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<tag> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<tag> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<tag> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        tag.add(new tag("dinner"));
          test = new Recipe("pancake","how to cook",tag);


     }
    @Test
    public void getNameGeneral() throws Exception {


        assertEquals("pancake",test.getName());

    }

    @Test
    public void getNameEmpty() throws Exception {
         test = new Recipe("","how to cook",tag);
        assertEquals("",test.getName());
    }

    @Test
    public void getNameLong() throws Exception {

         test = new Recipe("pancakesadddfadfsaasfafdfsfafadfafsafafsfaafasf","how to cook",tag);
        assertEquals("pancakesadddfadfsaasfafdfsfafadfafsafafsfaafasf",test.getName());

    }

    @Test
    public void getNameSpecialCharacters() throws Exception {

         test = new Recipe("!@#$%^&*()1234567890[]{}||.,?/><","how to cook",tag);
        assertEquals("!@#$%^&*()1234567890[]{}||.,?/><",test.getName());

    }



    @Test
    public void testGetDirectionValid() throws Exception {
        // General Case
        String testDirections = "1. boil water, 2. pinch of salt, 3 pour pasta cook 15 min";
         test = new Recipe("pancake",testDirections,tag);
        String returnedName = test.getDirection();
        assertEquals(testDirections, returnedName);
    }
    public void testGetDirectionNull() throws Exception {
        // Boundary cases
       String testDirections = ""; //empty string
        test = new Recipe("pancake",testDirections,tag);
        String returnedName = test.getDirection();
        assertEquals(testDirections, returnedName);
    }
    public void getDirectionLong() throws Exception {
        // long string
        String testDirections = "sa;dlfkjas;ldfjl;sadjflk;asdj;glasjdl;kgjas;ldgjasl;dgjaskl;dgjasdgjasd;lkgjad";
        test = new Recipe("pancake",testDirections,tag);
        String returnedName = test.getDirection();
        assertEquals(testDirections, returnedName);
    }
    public void getDirectionSpecial() throws Exception {
        String testDirections = "!@#$%^&*()1234567890[]{}||.,?/><"; // special characters
        test = new Recipe("pancake",testDirections,tag);
        String returnedName = test.getDirection();
        assertEquals(testDirections, returnedName);
    }


    @Test
    public void equalsSame() throws Exception {
        Recipe test = new Recipe("pancake","how to cook",tag);
        Recipe test1 = new Recipe("pancake","how to cook",tag);
        assertTrue(test.equals(test1));
    }

    @Test
    public void equalsDifferent() throws Exception {
        Recipe test = new Recipe("pancake","how to cook",tag);
        Recipe test1 = new Recipe("pancakeasd","how to cook",tag);
        assertFalse(test.equals(test1));
    }

    @Test
    public void equalsEmpty() throws Exception {
        tag=null;
        Recipe recipeOne = new Recipe("","",tag);
        Recipe recipeTwo = new Recipe("","",tag);
        assertTrue(recipeTwo.equals(recipeOne));
    }




}