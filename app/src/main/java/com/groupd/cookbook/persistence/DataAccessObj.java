package com.groupd.cookbook.persistence;

/**
 * Created by Zhang Jiashen on 2017/6/21.
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import com.groupd.cookbook.objects.Recipe;

public class DataAccessObj implements DataAccess{

    private Statement st1, st2, st3;
    private Connection c1;

    private String dbName;
    private String dbType;
    private ArrayList<Recipe> searchResult;
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

    public void close() throws myException {
         ResultSet rs2;
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));

        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public List<Recipe> getRecipe(String name) throws myException {
        ResultSet rs2 = null;
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
            throw new myException(processSQLError(e));
        }
        try
        {
            while (rs2.next())
            {
                myRecipeName = rs2.getString("Name");
                myDirection = rs2.getString("Direction");
                recipe = new Recipe( myRecipeName, myDirection,myTags);
                result.add(recipe);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
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
                myDirection = rs2.getString("Direction");
                myTags = getTags(name);
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
    private String getTags(String name) throws myException {
        ResultSet rs2 = null;
        String result = "";
        ArrayList<String> tags = new ArrayList<String>();
        try
        {
            cmdString = "Select * from RC Where UPPER(RName) = '"+name.toUpperCase()+"'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData()
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                tags.add(rs2.getString("CName"));
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        for(int i = 0;i<tags.size();i++){
            if(i!=tags.size()-1){
                result = result + tags.get(i)+",";
            }
            else{
                result = result + tags.get(i);
            }
        }
        return result;
    }
    public List<Recipe> getRecipeList() throws myException {
        List<Recipe> result = new ArrayList<Recipe>();
       getRecipeSequential(result);
        return result;
    }

    public String getRecipeSequential(List<Recipe> recipeResult) throws myException {
        ResultSet rs2 = null;
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
            throw new myException(processSQLError(e));
        }

        try
        {
            while (rs2.next())
            {
                myRecipeName = rs2.getString("Name");
                myTags = getTags(myRecipeName);
                myDirection = rs2.getString("Direction");
                recipe = new Recipe(myRecipeName, myDirection,myTags);
                recipeResult.add(recipe);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
            throw new myException(processSQLError(e));

        }

        return result;
    }



    public String insertRecipe(Recipe currentRecipe) throws myException {
        ResultSet rs2 = null;
        String values;
        String[]tags = currentRecipe.getRecipeTags().split(",");
        String recipes = "";
        String where;
        result = null;

        try
        {
            values = "'"+currentRecipe.getName()
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
        insertRecipeTags(currentRecipe.getRecipeTags(),currentRecipe.getName());
        return result;
    }
    private void insertRecipeTags(String tags, String rName) throws myException {
        String []myTags = tags.split(",");
        String values;
        for(int i = 0;i<myTags.length;i++) {
            try {
                 values = "'" + rName + "','"+myTags[i]+"'";
                cmdString = "Insert into RC " + " Values(" + values + ")";
                //System.out.println(cmdString);
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
        }
    }

    public String updateRecipe(Recipe currentRecipe) throws myException {
        ResultSet rs2 = null;
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Direction='" +currentRecipe.getDirection()
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
            throw new myException(processSQLError(e));
        }
        deleteRecipeTags(currentRecipe.getName());
        insertRecipeTags(currentRecipe.get,currentRecipe.getName());
        return result;
    }
    private void deleteRecipeTags(String name) throws myException{

    }
    public String deleteRecipe(Recipe currentRecipe)
    {
        ResultSet rs2 = null;
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
        ResultSet rs2 = null;
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
        ResultSet rs2 = null;
        String values;

        result = null;
        try
        {
            values = "'"+currentFavorite.getName()
                    +"', '" +currentFavorite.getRecipeTags()
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
                    +"', Tags='" +currentFavorite.getRecipeTags()
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
   public  List<Recipe> getSearchResult(){
        return searchResult;
    }
    public  void setSearchResult( ArrayList<Recipe> searchResult){
        this.searchResult = new ArrayList<Recipe>();
        for(int i = 0;i<searchResult.size();i++){
            this.searchResult.add(searchResult.get(i));
        }

    }
    public String search(ArrayList<Recipe> input){
        ResultSet rs2 = null;
        Recipe recipe;
        String myRecipeName, myTags, myDirection;
        myRecipeName = EOF;
        myTags = EOF;
        myDirection = EOF;
        result = null;
        String inputstr = input.get(0).getName();
        input.remove(0);

        try
        {
            cmdString = "Select * from R Where Upper(Name) Like '"+inputstr.toUpperCase()+"%'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if(rs2!=null) {
            try {
                while (rs2.next()) {
                    myRecipeName = rs2.getString("Name");
                    myTags = rs2.getString("tags");
                    myDirection = rs2.getString("Direction");
                    recipe = new Recipe(myRecipeName, myDirection, myTags);
                    input.add(recipe);
                }
                rs2.close();
            } catch (Exception e) {
                result = processSQLError(e);
            }
        }
        return result;
    }
}
 class myException extends Exception {

    public myException(String message){
        super(message);
    }

}