package com.groupd.cookbook.test.objects;

import android.support.annotation.NonNull;

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.step;
import com.groupd.cookbook.objects.tag;

import junit.framework.TestCase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Glenn on 2017-06-07.
 */
public class RecipeTest extends TestCase {
    private Recipe rcp;
    public RecipeTest(String arg0)
    {
        super(arg0);
    }
    public void testrecipe()throws myException{

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
        rcp = new Recipe("testname",stepss,tagss,"testneeds");
        assertTrue("testname".equalsIgnoreCase(rcp.getName()));
        assertTrue("testneeds".equalsIgnoreCase(rcp.getNeed()));
        //assertTrue("meat".equalsIgnoreCase(rcp.getRecipeTags().get(0).getTagsName()));
        //System.out.println(rcp.getRecipeTags().get(0).getTagsName());
        //assertTrue("a.".equalsIgnoreCase(rcp.getRecipeSteps().get(0).getStepsName()));


    }





}