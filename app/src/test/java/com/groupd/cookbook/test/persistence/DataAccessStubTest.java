package com.groupd.cookbook.test.persistence;

import android.support.annotation.NonNull;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.tag;
import com.groupd.cookbook.persistence.DataAccess;

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

    private static String dbName = Main.dbName;

    public DataAccessStubTest(String arg0)
    {
        super(arg0);
    }
    @Before
    public void setUp() throws myException {
        test = Services.createDataAccess(new DataAccessStub(dbName));
    }

    @After
    public void tearDown() {test = null;}
    @Test
    public void testGetRecipeValid() throws myException {
        List<tag> tag = new List<com.groupd.cookbook.objects.tag>() {
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

        tag.add(new tag("dinner,meet"));
        Recipe re = new Recipe("a",
                "Preheat oven to 425 degrees F (220 degrees C). Line a large baking sheet with aluminum foil and drizzle olive oil over foil. Place baking sheet in preheated oven.\n" +
                        "    Flatten chicken breasts so they are all about 1/4-inch thick. Season chicken with salt and pepper.\n" +
                        "    Mix flour and paprika together on a large plate. Beat eggs with salt and pepper in a shallow bowl. Mix bread crumbs and lemon zest together on a separate large plate. Dredge each chicken piece in flour mixture, then egg, and then bread crumbs mixture and set aside in 1 layer on a clean plate. Repeat with remaining chicken.\n" +
                        "    Remove baking sheet from oven and arrange chicken in 1 layer on the sheet. Drizzle more olive oil over each piece of coated chicken.\n" +
                        "    Bake in the preheated oven for 5 to 6 minutes. Flip chicken and continue baking until no longer pink in the center and the breading is lightly browned, 5 to 6 minutes more. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).",
                tag);
       assertEquals("error message",re,test.getRecipe("a"));
    }
     @Test
    public void testGetRecipeNull() throws myException {
         assertEquals("error message",null,test.getRecipe(null));
     }
     @Test
    public void testInsertRecipeValid() throws myException {
         List<tag> tag = new List<com.groupd.cookbook.objects.tag>() {
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
         Recipe n = new Recipe("as","adfa",tag);
         test.insertRecipe(n);
         ArrayList<String> ar = new ArrayList<>();
         ar.add("as");
         assertEquals("error message",ar,test.search("as"));
     }

    @Test
    public void testInsertRecipeEmpty() throws myException {


        ArrayList<String> ar = new ArrayList<>();

        assertEquals("error message",ar,test.search(""));
    }
    @Test
    public void testDeleteRecipeValid() throws myException {
        test.deleteRecipe("b");
        assertEquals("error message",null,test.getRecipe("b"));



    }

@Test
    public void testUpdateRecipeValid() throws myException {
    List<tag> tag = new List<com.groupd.cookbook.objects.tag>() {
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

    tag.add(new tag("breakfast"));
    Recipe re = new Recipe("d",
            "Cray. ok until browned on the other side.",
            tag);
    test.updateRecipe(re);
    assertEquals("error message",re,test.getRecipe("d"));}

    @Test
    public void testUpdateRecipeEmpty() throws myException {

        List<tag> tag = new List<com.groupd.cookbook.objects.tag>() {
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


        Recipe re = new Recipe("d",
                null,
                tag);
        test.updateRecipe(re);
        assertEquals("error message",re,test.getRecipe("d"));

    }

    @Test
    public void testGetRecipeListNotNull() throws myException {
        assertNotNull(test.getRecipeList());
    }

    @Test
    public void testSearchEmpty() throws myException {
        ArrayList<String> ar= new ArrayList<>();
       assertEquals("error message",ar,test.search(""));
    }

    @Test
    public void testSearchValid() throws myException {
        ArrayList<String> ar= new ArrayList<>();
        ar.add("c");
        assertEquals("error message",ar,test.search("c"));
    }
    @Test
    public void testGetRecipeSequential( ) throws myException {
        assertNotNull(test.getRecipeSequential());
    }

}




