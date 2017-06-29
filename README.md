# Cookbook
### Group 14
### Members: Glenn Jayasuriya, Jiashen Zhang, Siyu Pan, Tao Wu, Junwei Wang

# Iteration 2
Iteration 1 had hardcoded recipes and the user was able to search by the exact titles and view the recipe.
## Major Changes In Iteration 2
* Connected a HSQLDB database.
* Connected Add layout, so can now add recipes.
* Can now search by partial titles.
* Created a toolbar menu, replaced buttons with icons at top.
* Changed tags from just a string to it's own objects.
* Made 5 Categories, Veg, Meat, Breakfast, Lunch Dinner
* Can add and search by categories as well. (note use hashtags to search by tag, eg) #meat for meat recipes.
* View recipe now has buttons to update the recipe and delete viewing recipe. 

## Features we weren't able to do, moved to next iteration.
* User defined tags instead of categories
* Timer for recipes
* Some extra recipe info like prep time, easy to do just strings.
* Multi delete from menu
* User profile with preferences
* Favorites, we'll simply add as a special category next iteration.
* Suggestions below view recipe, we'll just show other recipes with same categories.
* History of actions *Note these last 2, we may not do because they seem unnecessary*
* Random Recipe Finder.

## Note on running
* Marker may need to uninstall the app and run the app again on the device so that the connections to the database are reset after reading the SC.script file in the assets db folder.

# Project Architecture

The project is broken up into 5 packages.
**Note Iteration 2 changes/update to pacakges will be in bold clearly appended to the bottom of each section** 
1. application
2. business
3. objects
4. persistence
5. presentation
----

## 1. Application
Contains the main and services java files.
The main java file starts the services file.
The services file creates, verifies connection and closes a Data Access Stub.
Services is used by other recipes to get access to the Data stub.

**No change in terms of new files/structure**

---

## 2. Business
Contains AccessRecipe and search java files.
These files handle the logic of our program.
AccessRecipe through the use of the Services java class gets access to the Data access stub.
AccessRecipe gets recipes and search by name. You can also get a copy of what's inside the data stub.
Search java file is linked to the Search layout in the resources package.

**Current State of Logic**: Currently we can only search for recipes in our menu of recipes. We plan to add more features in future iterations.

## 3. Objects
We have one object java file for the recipes. The structure of our recipes containts a name (for the title), directions (steps for the recipe), and tags.
We plan on having a search by tag feature in the future.

## 4. Persistence
Contains our DataAccessStub java file. This is our "database"/data storage for this iteration. 
We are using an arraylist to hold the list of recipes.

## 5. Presentation
Contains our files that link to our layour files in the resources folders.
These files handle logic in regards to gui handling. For instance what to do when this button of the GUI is pressed.
It works in relation to the logic files in the business class to connect with the layout files.

-----
Layouts in Resource Folder
----

For this iteration we have implemented 
1. General menu (test_list) that shows a list of our recipes.
2. Search menu (search) that finds a recipe based on a title.
3. View reciew (test_show) this shows the recipe that was selected by the user.
There are also other layouts for adding recipes we plan to implement into our next iteration.

---
# Where to find the log:
Log can be found here: https://docs.google.com/document/d/1mpULj1nz0k4Bc0JuW300F8PiBF1977kL5Xz8X_DPqPk/edit?usp=sharing
