package com.groupd.cookbook.test.persistence;

import android.support.annotation.NonNull;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.business.AccessRecipe;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.step;
import com.groupd.cookbook.objects.tag;
import com.groupd.cookbook.persistence.DataAccess;
import com.groupd.cookbook.persistence.DataAccessObj;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;


public class DataAccessStubTest extends TestCase {
    DataAccess test;
    Recipe rcp=null;
    private static String dbName = Main.dbName;

    public DataAccessStubTest(String arg0)
    {
        super(arg0);
    }
    @Before
    public void setUp() throws myException {

        DataAccess dataAccess;

        Services.closeDataAccess();
        dataAccess = Services.createDataAccess(new DataAccessStub(dbName));
        step tstep = new step("a.");
        List<step> stepss = new List<step>() {
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
        tag ttag = new tag("meat");
        List<tag> tagss = new List<tag>() {
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
        rcp = new Recipe("testname", stepss, tagss, "testneeds");
        test = Services.getDataAccess(dbName);

    }

    @After
    public void tearDown() {test = null;}
    @Test
    public void dataAccessTest(){

        try{

        }catch(Exception e){}

        try{
            test.insertRecipe(rcp);
        }catch(Exception e){}

        try{
            assertTrue("testname".equalsIgnoreCase(test.getRecipe("testname").getName()));
        }catch(Exception e){}


        try{
            Recipe temp=test.getRecipe("testname");
            temp.getRecipeTags().add(new tag("dinner"));
            test.updateRecipe(temp);
            assertTrue(test.getRecipe("testname").getRecipeTags().get(1).getTagsName().equalsIgnoreCase("dinner"));
        }catch(Exception e){}













    }
}


