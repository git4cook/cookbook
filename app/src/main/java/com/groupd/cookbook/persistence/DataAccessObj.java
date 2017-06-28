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
    private ResultSet rs2, rs3, rs4, rs5;
    private String dbName;
    private String dbType;
    private String cmdString;
    private int updateCount;
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
        Recipe recipe;
        List<Recipe> recipeResult = new ArrayList<Recipe>();
        String myRecipeName, myDirection;
        myRecipeName = EOF;
        String aaa = "";
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
                myDirection = rs2.getString("Direction");
                recipe = new Recipe(myRecipeName, myDirection,null);
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
        String values;
        String[]tags = tagsInString(currentRecipe.getRecipeTags()).split(",");

        try
        {
            values = "'"+currentRecipe.getName()
                    +"', '" +currentRecipe.getDirection()
                    +"'";
            cmdString = "Insert into R " +" Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
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
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
        }
    }

    public void updateRecipe(Recipe currentRecipe) throws myException {
        String values;
        String where;

        try
        {
            // Should check for empty values and not update them
            values = "Direction='" +currentRecipe.getDirection()
                    +"'";
            where = "where UPPER(Name)='" +currentRecipe.getName().toUpperCase()+"'";
            cmdString = "Update R " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
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
        } catch (Exception e) {
            throw new myException(processSQLError(e));
        }
    }
    public void deleteRecipe(Recipe currentRecipe) throws myException {
        String values;

        try
        {
            values = currentRecipe.getName();
            cmdString = "Delete from R where UPPER(Name)='" +values.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        deleteRecipeTags(currentRecipe.getName());
    }

    public List<String> getFavoriteSequential() throws myException {
        Recipe recipe;
        List<String> favoriteResult = new ArrayList<String>();
        String myRecipeName;
        myRecipeName = EOF;

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
        String values;

        try
        {
            cmdString = "Insert into R " +" Values('" +currentFavorite +"')";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
    }


    public void deleteFavorite(String currentFavorite) throws myException {

        try
        {
            cmdString = "Delete from F where UPPER(Name)='" +currentFavorite.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
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
    public ArrayList<String> search(String input) throws myException {
        Recipe recipe;
        String myRecipeName, myTags, myDirection;
        myRecipeName = EOF;
        myTags = EOF;
        myDirection = EOF;
        ArrayList<String> result = new ArrayList<String>();
        String inputstr[] = input.split("#");
        if(inputstr.length==1) {
            try {
                cmdString = "Select * from R Where Upper(Name) Like %'" + inputstr[0].toUpperCase() + "%'";
                rs2 = st1.executeQuery(cmdString);


                //ResultSetMetaData md2 = rs2.getMetaData();
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
            if (rs2 != null) {
                try {
                    while (rs2.next()) {
                        result.add(rs2.getString("Name"));
                    }
                    rs2.close();
                } catch (Exception e) {
                    throw new myException(processSQLError(e));
                }
            }
        }
        else if(inputstr.length==2) {
            String[] tags = inputstr[1].split(",");
            for(int i = 0;i<tags.length;i++) {
                try {
                    cmdString = "Select * from R,RC Where Upper(R.Name)=Upper(RC.RName)" +
                            ",Upper(RC.CName)='"+tags[i].toUpperCase()+"',Upper(R.Name) Like %'"
                            + inputstr[0].toUpperCase() + "%'";
                    rs2 = st1.executeQuery(cmdString);


                    //ResultSetMetaData md2 = rs2.getMetaData();
                } catch (Exception e) {
                    throw new myException(processSQLError(e));
                }
                if (rs2 != null) {
                    try {
                        while (rs2.next()) {
                            result.add(rs2.getString("Name"));
                        }
                        rs2.close();
                    } catch (Exception e) {
                        throw new myException(processSQLError(e));
                    }
                }
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
