package com.groupd.cookbook.test.persistence;

import android.support.annotation.NonNull;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;
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
        Recipe recipe;
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
        //1.
        recipes = new ArrayList<Recipe>();
        tag.add(new tag("dinner,meet"));
        recipe = new Recipe("a",
                "Preheat oven to 425 degrees F (220 degrees C). Line a large baking sheet with aluminum foil and drizzle olive oil over foil. Place baking sheet in preheated oven.\n" +
                        "    Flatten chicken breasts so they are all about 1/4-inch thick. Season chicken with salt and pepper.\n" +
                        "    Mix flour and paprika together on a large plate. Beat eggs with salt and pepper in a shallow bowl. Mix bread crumbs and lemon zest together on a separate large plate. Dredge each chicken piece in flour mixture, then egg, and then bread crumbs mixture and set aside in 1 layer on a clean plate. Repeat with remaining chicken.\n" +
                        "    Remove baking sheet from oven and arrange chicken in 1 layer on the sheet. Drizzle more olive oil over each piece of coated chicken.\n" +
                        "    Bake in the preheated oven for 5 to 6 minutes. Flip chicken and continue baking until no longer pink in the center and the breading is lightly browned, 5 to 6 minutes more. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).",
                tag);
        recipes.add(recipe);

        //2.
        //recipes = new ArrayList<Recipe>();
        tag.add(new tag("dinner"));
        recipe = new Recipe("b",
                "Preheat an oven to 350 degrees F (175 degrees C). Grease a 9-inch square cake pan. Whisk the flour, baking powder, and salt together in a bowl; set aside.\n" +
                        "Beat the shortening and sugar with an electric mixer in a large bowl until light and fluffy. Beat in the egg and vanilla extract until smooth. Pour in the flour mixture alternately with the milk, mixing until just incorporated. Pour the batter into prepared pan.\n" +
                        "Bake in the preheated oven until a toothpick inserted into the center comes out clean, about 25 minutes. Cool in the pans for 10 minutes before removing to cool completely on a wire rack.",
                tag);
        recipes.add(recipe);

        //3.
        //recipes = new ArrayList<Recipe>();
        tag.add(new tag("meat,lunch,dinner"));
        recipe = new Recipe("c",
                "Heat 1 tablespoon olive oil in a skillet over medium heat. Cook and stir onion and red pepper with a pinch of salt in hot oil until onion is soft and translucent, 5 to 7 minutes. Remove from heat and cool to room temperature.\n" +
                        "Stir sausage, minced tentacles, onion mixture, parsley, egg, smoked paprika, salt, and pepper together in a bowl until evenly combined. Transfer mixture to a piping bag.\n" +
                        "Pipe sausage mixture into tubes, filling about each tube about 2/3 full. Thread a toothpick through the top of each tube to fasten the opening together. Place stuffed tubes on a plate, cover the plate with plastic wrap, and refrigerate until completely chilled, about 1 hour.\n" +
                        "Preheat an outdoor grill for medium-high heat and lightly oil the grate.\n" +
                        "Brush tubes with remaining olive oil to coat on all sides and season with salt.\n" +
                        "Cook stuffed calamari on the preheated grill, turning occasionally, until stuffing is cooked through, 10 to 12 minutes. An instant-read thermometer inserted into the center should read at least 155 degrees F (68 degrees C).",
                tag);
        recipes.add(recipe);

        //4.
        //recipes = new ArrayList<Recipe>();
        tag.add(new tag("breakfast"));
        recipe = new Recipe("d",
                "Combine milk with vinegar in a medium bowl and set aside for 5 minutes to \"sour\".\n" +
                        "Combine flour, sugar, baking powder, baking soda, and salt in a large mixing bowl. Whisk egg and butter into \"soured\" milk. Pour the flour mixture into the wet ingredients and whisk until lumps are gone.\n" +
                        "Heat a large skillet over medium heat, and coat with cooking spray. Pour 1/4 cupfuls of batter onto the skillet, and cook until bubbles appear on the surface. Flip with a spatula, and cook until browned on the other side.",
                tag);
        recipes.add(recipe);

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

    public List<Recipe> getRecipeSequential()
    {

        return null;
    }


    public ArrayList<String> search(String input) throws myException{
        return null;
    }
}





