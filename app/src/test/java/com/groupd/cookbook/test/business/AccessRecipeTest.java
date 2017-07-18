

package com.groupd.cookbook.test.business;

import android.support.annotation.NonNull;

        import com.groupd.cookbook.application.Main;
        import com.groupd.cookbook.application.Services;
        import com.groupd.cookbook.business.AccessRecipe;
        import com.groupd.cookbook.objects.Recipe;
        import com.groupd.cookbook.objects.step;
        import com.groupd.cookbook.objects.tag;

        import junit.framework.TestCase;

        import java.util.Collection;
        import java.util.Iterator;
        import java.util.List;
        import java.util.ListIterator;
import com.groupd.cookbook.objects.myException;
/*
import android.support.annotation.NonNull;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;

import com.groupd.cookbook.objects.step;
import com.groupd.cookbook.objects.tag;
import com.groupd.cookbook.test.persistence.DataAccessStub;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by siyu on 2017/6/27.
 */

public class AccessRecipeTest extends TestCase {
    private AccessRecipe ar;
    private Recipe a;
    private static String dbName = Main.dbName;

    public AccessRecipeTest(String arg0) {
        super(arg0);
    }

    public void testar() throws myException {


        AccessRecipe ar;
        Recipe rcp;
        String result;
        try{
            Services.closeDataAccess();
        }catch(Exception e){}

        try{
            Services.createDataAccess(Main.dbName);
        }catch(Exception e){

        }


        ar = new AccessRecipe();
        step tstep = new step("a.");
        List<step> stepss= new List<step>() {
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
            public Iterator<step> iterator() {
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
            public boolean add(step step) {
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
            public boolean addAll(@NonNull Collection<? extends step> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends step> c) {
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
            public step get(int index) {
                return null;
            }

            @Override
            public step set(int index, step element) {
                return null;
            }

            @Override
            public void add(int index, step element) {

            }

            @Override
            public step remove(int index) {
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
            public ListIterator<step> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<step> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<step> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        stepss.add(tstep);
        //assertEquals(null,stepss);
        tag ttag = new tag("meat");
        List<tag> tagss=new List<tag>() {
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
        tagss.add(ttag);
        //assertEquals(null,tagss);


        rcp = new Recipe("testname",stepss,tagss,"testneeds");
        try{
            ar.insertRecipe(rcp);
        }catch(Exception e){}

        try{
            assertTrue("testname".equals(ar.getRecipe("testname").getName()));
        }catch(Exception e){}

        try{
            assertTrue("a.".equalsIgnoreCase(ar.getRecipe("testname").getRecipeSteps().get(0).getStepsName()));
        }catch(Exception e){}

        try{
            assertTrue("meat".equalsIgnoreCase(ar.getRecipe("testname").getRecipeTags().get(0).getTagsName()));
        }catch(Exception e){}

        try{
            assertTrue("testneeds".equalsIgnoreCase(ar.getRecipe("testname").getNeed()));
        }catch(Exception e){}

        try{
            ar.deleteRecipe("testname");
        }catch(Exception e){}

        try{
            assertFalse(ar.findRecipe("testname"));
        }catch(Exception e){}


        // Need to add some more tests to exercise all CRUD operations
        try{
            Services.closeDataAccess();
        }catch(Exception e){}

        ar=null;
        System.out.println("Finished Integration test of AccessStudents to persistence");
    }















}
