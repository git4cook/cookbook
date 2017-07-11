//
//
//package com.groupd.cookbook.test.business;
//
//
//import android.support.annotation.NonNull;
//
//import com.groupd.cookbook.application.Main;
//import com.groupd.cookbook.application.Services;
//import com.groupd.cookbook.business.AccessRecipe;
//import com.groupd.cookbook.objects.Recipe;
//import com.groupd.cookbook.objects.myException;
//import com.groupd.cookbook.objects.tag;
//import com.groupd.cookbook.test.persistence.DataAccessStub;
////import com.groupd.cookbook.test.persistence.DataAccessStub;
//
//import junit.framework.TestCase;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotNull;
//
///**
// * Created by siyu on 2017/6/27.
// */
//
//public class AccessRecipeTest extends TestCase {
//    private AccessRecipe test;
//
//    private Recipe a ;
//    private Recipe b;
//
//    private List<tag> t;
//    private static String dbName = Main.dbName;
//    public AccessRecipeTest(String arg0)
//    {
//        super(arg0);
//    }
//    @Before
//    public void setUp() throws myException {
//            Services.closeDataAccess();
//            Services.createDataAccess(new DataAccessStub(dbName));
//            test = new AccessRecipe();
//            t = new List<tag>() {
//                @Override
//                public int size() {
//                    return 0;
//                }
//
//                @Override
//                public boolean isEmpty() {
//                    return false;
//                }
//
//                @Override
//                public boolean contains(Object o) {
//                    return false;
//                }
//
//                @NonNull
//                @Override
//                public Iterator<tag> iterator() {
//                    return null;
//                }
//
//                @NonNull
//                @Override
//                public Object[] toArray() {
//                    return new Object[0];
//                }
//
//                @NonNull
//                @Override
//                public <T> T[] toArray(@NonNull T[] a) {
//                    return null;
//                }
//
//                @Override
//                public boolean add(tag tag) {
//                    return false;
//                }
//
//                @Override
//                public boolean remove(Object o) {
//                    return false;
//                }
//
//                @Override
//                public boolean containsAll(@NonNull Collection<?> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean addAll(@NonNull Collection<? extends tag> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean addAll(int index, @NonNull Collection<? extends tag> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean removeAll(@NonNull Collection<?> c) {
//                    return false;
//                }
//
//                @Override
//                public boolean retainAll(@NonNull Collection<?> c) {
//                    return false;
//                }
//
//                @Override
//                public void clear() {
//
//                }
//
//                @Override
//                public tag get(int index) {
//                    return null;
//                }
//
//                @Override
//                public tag set(int index, tag element) {
//                    return null;
//                }
//
//                @Override
//                public void add(int index, tag element) {
//
//                }
//
//                @Override
//                public tag remove(int index) {
//                    return null;
//                }
//
//                @Override
//                public int indexOf(Object o) {
//                    return 0;
//                }
//
//                @Override
//                public int lastIndexOf(Object o) {
//                    return 0;
//                }
//
//                @Override
//                public ListIterator<tag> listIterator() {
//                    return null;
//                }
//
//                @NonNull
//                @Override
//                public ListIterator<tag> listIterator(int index) {
//                    return null;
//                }
//
//                @NonNull
//                @Override
//                public List<tag> subList(int fromIndex, int toIndex) {
//                    return null;
//                }
//            };
//
//        a = new Recipe("pancake","how to cook",t);
//          b = new Recipe ("","",t);
//
//
//    }
//    @After
//    public void tearDown(){
//        test = null;
//    }
//
//    @Test
//    public void testGetRecipeValid() throws myException {
//        test.insertRecipe(a);
//         Recipe result = test.getRecipe("pancake");
//            assertEquals("error message",a,result);
//    }
//   @Test
//    public void testGetRecipeNull() throws myException {
//        String x = "";
//       test.insertRecipe(b);
//        Recipe result = test.getRecipe(x);
//        assertEquals("error message",b,result);
//    }
//
//    @Test
//    public void testFindRecipeValid() throws myException {
//        test.insertRecipe(a);
//        boolean result = test.findRecipe("pancake");
//        assertEquals("error message",true,result);
//    }
//
//    @Test
//    public void testFindRecipeNull() throws myException {
//        String x = null;
//        boolean result = test.findRecipe(x);
//        assertEquals("error message",false,result);
//    }
//
//
//    @Test
//    public void testInsertRecipeNull() throws myException {
//
//       test.insertRecipe(b);
//        assertEquals("error message",b,test.getRecipe(""));
//    }
//    @Test
//    public void testInsertRecipeValid() throws myException {
//        test.insertRecipe(a);
//        assertEquals("error message",a,test.getRecipe("pancake"));
//
//    }
//    @Test
//    public void testUpdateRecipeValid() throws myException {
//        t.add(new tag("dinner"));
//
//        test.updateRecipe(new Recipe("pancake","change",t));
//        assertNotSame("error message",a,test.getRecipe("pancake"));
//    }
//
//    @Test
//    public void testDeleteRecipeEmpty() throws myException {
//        String x = "";
//         test.deleteRecipe(x);
//        assertEquals("error message",false,test.findRecipe(x));
//    }
//    @Test
//    public void testDeleteRecipeValid() throws myException {
//        test.deleteRecipe("pancake");
//        assertEquals("error message",false,test.findRecipe("pancake"));
//    }
//    @Test
//    public void testSearchValid() throws myException {
//        ArrayList<String> ar = new ArrayList<>();
//        ar.add("pancake");
//        test.insertRecipe(a);
//        assertEquals("error message",ar,test.search("pancake"));
//    }
//
//    @Test
//    public void testSearchNull() throws myException {
//        ArrayList<String> ar = new ArrayList<>();
//
//        assertEquals("error message",null,test.search(null));
//    }
//
//}
