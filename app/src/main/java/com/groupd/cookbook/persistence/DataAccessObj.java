package com.groupd.cookbook.persistence;

/**
 * Created by Zhang Jiashen on 2017/6/21.
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.persistence.DataAccess;

public class DataAccessObj implements DataAccess{

    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Recipe> recipes;
    private ArrayList<Recipe> favorites;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObj(String dbName)
    {
        this.dbName = dbName;
    }
    public void open(String dbPath)
    {
        String url;
        try
        {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();


        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public List<Recipe> getR(String name){
        Recipe recipe;
        String myRecipeName, myTags, myDirection;
        myRecipeName = EOF;
        myTags = EOF;
        myDirection = EOF;

        List<Recipe> result =new ArrayList<Recipe>();
//        recipe = new Recipe( "aaa", "bbb","ccc");
//        result.add(recipe);
        try
        {
            cmdString = "Select * from R Where UPPER(Name) = '"+name.toUpperCase()+"'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                myRecipeName = rs2.getString("Name");
                myTags = rs2.getString("Tags");
                myDirection = rs2.getString("Direction");
                recipe = new Recipe( myRecipeName, myDirection,myTags);
                result.add(recipe);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return result;
    }
    public List<Recipe> getRecipeList(){
        List<Recipe> result = new ArrayList<Recipe>();
       getRecipeSequential(result);
        return result;
    }

    public String getRecipeSequential(List<Recipe> recipeResult)
    {
        Recipe recipe;
        String myRecipeName, myTags, myDirection;
        myRecipeName = EOF;
        myTags = EOF;
        myDirection = EOF;

        result = null;
        try
        {
            cmdString = "Select * from R";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
             result = processSQLError(e);
        }

        try
        {
            while (rs2.next())
            {
                myRecipeName = rs2.getString("Name");
                myTags = rs2.getString("tags");
                myDirection = rs2.getString("Direction");
                recipe = new Recipe(myRecipeName, myDirection,myTags);
                recipeResult.add(recipe);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }



    public String insertRecipe(Recipe currentRecipe)
    {
        String values;

        result = null;
        try
        {
            values = currentRecipe.getName()
                    +"', '" +currentRecipe.getTags()
                    +"', '" +currentRecipe.getDirection()
                    +"'";
            cmdString = "Insert into R " +" Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateRecipe(Recipe currentRecipe)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Tags='" +currentRecipe.getTags()
                    +"', Direction='" +currentRecipe.getDirection()
                    +"'";




            where = "where UPPER(Name)='" +currentRecipe.getName().toUpperCase()+"'";
            cmdString = "Update R " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteRecipe(Recipe currentRecipe)
    {
        String values;

        result = null;
        try
        {
            values = currentRecipe.getName();
            cmdString = "Delete from R where UPPER(Name)='" +values.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String getFavoriteSequential(List<Recipe> favoriteResult)
    {
        Recipe recipe;
        String myID, myRecipeName, myTags, myDirection;
        myRecipeName = EOF;
        myTags = EOF;
        myID = EOF;
        myDirection = EOF;

        result = null;
        try
        {
            cmdString = "Select * from R";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                myID = rs2.getString("RecipeID");
                myRecipeName = rs2.getString("Name");
                myTags = rs2.getString("tags");
                myDirection = rs2.getString("Direction");
                recipe = new Recipe(myRecipeName, myDirection,myTags);
                favoriteResult.add(recipe);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }



    public String insertFavorite(Recipe currentFavorite)
    {
        String values;

        result = null;
        try
        {
            values = currentFavorite.getName()
                    +"', '" +currentFavorite.getTags()
                    +"', '" +currentFavorite.getDirection()
                    +"'";
            cmdString = "Insert into R " +" Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String  updateFavorite(Recipe currentFavorite)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Name='" +currentFavorite.getName()
                    +"', Tags='" +currentFavorite.getTags()
                    +"', Direction='" +currentFavorite.getDirection()
                    +"'";
            where = "where UPPER(Name)='" +currentFavorite.getName().toUpperCase()+"'";
            cmdString = "Update F " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteFavorite(Recipe currentFavorite)
    {
        String values;

        result = null;
        try
        {
            values = currentFavorite.getName();
            cmdString = "Delete from F where UPPER(Name)='" +values.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
