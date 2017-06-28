package com.groupd.cookbook.persistence;

/**
 * Created by Zhang Jiashen on 2017/6/21.
 */
import java.util.ArrayList;
import java.util.List;

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.myException;

public interface DataAccess {
    void open(String string) throws myException;
    void close() throws myException;

    List<Recipe> getRecipeSequential() throws myException;

    void insertRecipe(Recipe recipe) throws myException;

    void updateRecipe(Recipe recipe) throws myException;

    void deleteRecipe(Recipe recipe) throws myException;

    //List<String> getFavoriteSequential() throws myException;

    //void insertFavorite(String favorite) throws myException;


    //void deleteFavorite(String favorite) throws myException;
    Recipe getRecipe(String name) throws myException;
    List<Recipe> getRecipeList() throws myException;
    ArrayList<String> search(String input) throws myException;
}




//INSERT INTO RECIPES VALUES('10000-Baked Chicken Schnitzel','olive oil,chicken,salt,black pepper,flour,paprika,eggs,bread,lemon',Preheat oven to 425 degrees F (220 degrees C). Line a large baking sheet with aluminum foil and drizzle olive oil over foil. Place baking sheet in preheated oven.\nFlatten chicken breasts so they are all about 1/4-inch thick. Season chicken with salt and pepper.\nMix flour and paprika together on a large plate. Beat eggs with salt and pepper in a shallow bowl. Mix bread crumbs and lemon zest together on a separate large plate. Dredge each chicken piece in flour mixture, then egg, and then bread crumbs mixture and set aside in 1 layer on a clean plate. Repeat with remaining chicken.\nRemove baking sheet from oven and arrange chicken in 1 layer on the sheet. Drizzle more olive oil over each piece of coated chicken.\n Bake in the preheated oven for 5 to 6 minutes. Flip chicken and continue baking until no longer pink in the center and the breading is lightly browned, 5 to 6 minutes more. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C).')
