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
import com.groupd.cookbook.objects.myException;
import com.groupd.cookbook.objects.tag;

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
    public void open(String dbPath) throws myException {
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
            throw new myException(processSQLError(e));
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

    public Recipe getRecipe(String name) throws myException {
        ResultSet rs2 = null;
        Recipe recipe = null;
        String myRecipeName, myDirection;
        myRecipeName = EOF;
        myDirection = EOF;
        List<tag> tags = new ArrayList<tag>();
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
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        tags = getTags(name);
        recipe = new Recipe( myRecipeName, myDirection,tags);
        return recipe;
    }
    private List<tag> getTags(String name) throws myException {
        ResultSet rs2 = null;
        String result = "";
        ArrayList<tag> tags = new ArrayList<tag>();
        try
        {
            cmdString = "Select * from RC Where UPPER(RName) = '"+name.toUpperCase()+"'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData()
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        try
        {
            while (rs2.next())
            {
                tags.add(new tag(rs2.getString("CName")));
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }

        return tags;
    }
    public List<Recipe> getRecipeList() throws myException {
        List<Recipe> result = new ArrayList<Recipe>();
       return getRecipeSequential();
    }

    public List<Recipe> getRecipeSequential( ) throws myException {
        ResultSet rs2 = null;
        Recipe recipe;
        List<Recipe> recipeResult = new ArrayList<Recipe>();
        String myRecipeName, myDirection;
        myRecipeName = EOF;
        List<tag> myTags;
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
            throw new myException(processSQLError(e));

        }

        return recipeResult;
    }



    public void insertRecipe(Recipe currentRecipe) throws myException {
        ResultSet rs2 = null;
        String values;
        String[]tags = tagsInString(currentRecipe.getRecipeTags()).split(",");
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
            throw new myException(processSQLError(e));
        }

        insertRecipeTags(tagsInString(currentRecipe.getRecipeTags()),currentRecipe.getName());
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

    public void updateRecipe(Recipe currentRecipe) throws myException {
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
            throw new myException(processSQLError(e));
        }
        deleteRecipeTags(currentRecipe.getName());
        insertRecipeTags(tagsInString(currentRecipe.getRecipeTags()),currentRecipe.getName());
    }
    private void deleteRecipeTags(String name) throws myException{
        String values;
            try {
                cmdString = "Delete From RC Where RName='"+name+"'";
                //System.out.println(cmdString);
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
    }
    public void deleteRecipe(Recipe currentRecipe) throws myException {
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
            throw new myException(processSQLError(e));
        }
        deleteRecipeTags(currentRecipe.getName());
    }

    public List<String> getFavoriteSequential() throws myException {
        ResultSet rs2 = null;
        Recipe recipe;
        List<String> favoriteResult = new ArrayList<String>();
        String myRecipeName;
        myRecipeName = EOF;

        result = null;
        try
        {
            cmdString = "Select * from R";
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
                favoriteResult.add(myRecipeName);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }

        return favoriteResult;
    }



    public void insertFavorite(String currentFavorite) throws myException {
        ResultSet rs2 = null;
        String values;

        result = null;
        try
        {
            cmdString = "Insert into R " +" Values('" +currentFavorite +"')";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
    }


    public void deleteFavorite(String currentFavorite) throws myException {
        String values;

        result = null;
        try
        {
            cmdString = "Delete from F where UPPER(Name)='" +currentFavorite.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
    }

    public String checkWarning(Statement st, int updateCount) throws myException {
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
            throw new myException(processSQLError(e));
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
    public String search(ArrayList<Recipe> input) throws myException {
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
            cmdString = "Select * from R Where Upper(Name) Like %'"+inputstr.toUpperCase()+"%'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        if(rs2!=null) {
            try {
                while (rs2.next()) {
                    myRecipeName = rs2.getString("Name");
                    myTags = rs2.getString("tags");
                    myDirection = rs2.getString("Direction");
                    recipe = new Recipe(myRecipeName, myDirection, tagsInObj(myTags));
                    input.add(recipe);
                }
                rs2.close();
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
        }
        return result;
    }
    private List<tag >tagsInObj(String tags){
        String temp[] = tags.split(",");
        ArrayList<tag> tagsInObj = new ArrayList<tag>();
        for(int i = 0;i<temp.length;i++){
            tagsInObj.add(new tag(temp[i]));
        }
        return tagsInObj;
    }

    private String tagsInString(List<tag> tags){
        String result = tags.get(0).getTagsName();
        for(int i = 1;i<tags.size();i++){
            result = result+","+tags.get(i).getTagsName();
        }
        return result;
    }
}
