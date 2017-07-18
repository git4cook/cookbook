package com.groupd.cookbook.test.persistence;

import android.support.annotation.NonNull;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.step;
import com.groupd.cookbook.objects.tag;
import com.groupd.cookbook.persistence.DataAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class DataAccessStub  implements DataAccess
{
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Recipe> recipes;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public void open(String dbName)
    {
        Recipe rcp;
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
        recipes.add(rcp);

        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }



    public ArrayList<Recipe> getRecipeList(){
        return recipes;
    }

    public void insertRecipe(Recipe currentRecipe)
    {
        // don't bother checking for duplicates
        recipes.add(currentRecipe);

    }

    public void updateRecipe(Recipe currentRecipe)
    {
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            recipes.set(index, currentRecipe);
        }

    }

    public void deleteRecipe(Recipe currentRecipe)
    {
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            recipes.remove(index);
        }

    }

    public void deleteRecipe(String name)
    {
        for(int i =0;i<recipes.size();i++) {
            if(recipes.get(i).getName()==name)
                recipes.remove(i);
        }
    }

    public Recipe getRecipe(String name) {
        for(int i =0;i<recipes.size();i++) {
            if(recipes.get(i).getName()==name)
                return recipes.get(i);
        }
        return null;
    }

    public List<Recipe> getRecipeSequential( )
    {
        List<Recipe> recipeResult = new ArrayList<Recipe>();
        recipeResult.addAll(recipes);
        return recipeResult;
    }



    public ArrayList<String> search(String name) throws myException{

        ArrayList<String> result = new ArrayList<String>();
        if(name ==null)
            return null;
        for(int i=0;i<recipes.size();i++){
            if(recipes.get(i).getName()==name)
                result.add(recipes.get(i).getName());
        }
        return result;
    }
}





