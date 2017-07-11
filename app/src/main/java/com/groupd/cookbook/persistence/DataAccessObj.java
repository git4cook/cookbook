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
import com.groupd.cookbook.objects.step;
import com.groupd.cookbook.objects.tag;

public class DataAccessObj implements DataAccess{

    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;
    private String dbName;
    private String dbType;
    private String cmdString;
    private String result;
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
        List<step> steps = new ArrayList<step>();
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
                //myDirection = rs2.getString("Direction");
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        steps = getSteps(name);
        tags = getTags(name);
        recipe = new Recipe( myRecipeName, steps, tags,getNeed(name));
        return recipe;
    }

    private String getNeed(String name) throws myException {
        String result = "";
        try
        {
            cmdString = "Select * from RN Where UPPER(RName) = '"+name.toUpperCase()+"'";
            rs2 = st1.executeQuery(cmdString);
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        try
        {
            while (rs2.next())
            {
                result = rs2.getString("Need");
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        return result;
    }
    private List<step> getSteps(String name) throws myException {
        String result = "";
        ArrayList<step> steps = new ArrayList<step>();
        try
        {
            cmdString = "Select * from RS Where UPPER(STEP) = '"+name.toUpperCase()+"'";
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
                steps.add(new step(rs2.getString("CName")));
            }
            rs2.close();
        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }

        return steps;
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
                recipe = new Recipe(myRecipeName, null, null,"");
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
        result = null;
        try
        {
            /*
            values = "'"+currentRecipe.getName()
                    +"', '" +currentRecipe.getDirection()
                    +"'";
                    */

            values = "'"+currentRecipe.getName()+"'";
            cmdString = "Insert into R  Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            if(result !=null){
                throw new myException(result);
            }

        }

        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        insertRecipeSteps(currentRecipe.getRecipeSteps(),currentRecipe.getName());
        insertRecipeTags(currentRecipe.getRecipeTags(),currentRecipe.getName());
        insertNeed(currentRecipe.getName(),currentRecipe.getNeed());
    }
    public void insertNeed(String currentRecipe,String need) throws myException {
        String values;
        result = null;
        try
        {
            /*
            values = "'"+currentRecipe.getName()
                    +"', '" +currentRecipe.getDirection()
                    +"'";
                    */

            values = "'" + currentRecipe + "','"+need+"'";
            cmdString = "Insert into RN  Values(" +values +")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            if(result !=null){
                throw new myException(result);
            }

        }

        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }

    }

    public void insertRecipeSteps(List<step> steps, String rName) throws myException {
        String values;
        for(int i = 0;i<steps.size();i++) {
            try {
                values = "'" + rName + "','"+steps.get(i).getStepsName()+"'";
                cmdString = "Insert into RS " + " Values(" + values + ")";
                //System.out.println(cmdString);
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
                if(result !=null){
                    throw new myException("insertag steps");
                }
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
        };
    }

    private void insertRecipeTags(List<tag> tags, String rName) throws myException {
        String values;
        for(int i = 0;i<tags.size();i++) {
            try {
                values = "'" + rName + "','"+tags.get(i).getTagsName()+"'";
                cmdString = "Insert into RC " + " Values(" + values + ")";
                //System.out.println(cmdString);
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
                if(result !=null){
                    throw new myException("insertag");
                }
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
        }
    }

    public void updateRecipe(Recipe currentRecipe) throws myException {

        deleteRecipeSteps(currentRecipe.getName());
        insertRecipeSteps(currentRecipe.getRecipeSteps(),currentRecipe.getName());
        deleteRecipeTags(currentRecipe.getName());
        insertRecipeTags(currentRecipe.getRecipeTags(),currentRecipe.getName());
        deleteNeed(currentRecipe.getName());
        insertNeed(currentRecipe.getName(),currentRecipe.getNeed());
    }

    private void deleteRecipeSteps(String name) throws myException{
        String values;
        try {
            cmdString = "Delete from RS where Upper(RName)='" +name.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

        }
        catch (Exception e) {
            throw new myException(processSQLError(e));
        }
    }

    private void deleteNeed(String name) throws myException{
        String values;
        try {
            cmdString = "Delete from RN where Upper(RName)='" +name.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

        }
        catch (Exception e) {
            throw new myException(processSQLError(e));
        }
    }

    private void deleteRecipeTags(String name) throws myException{
        String values;
        try {
            cmdString = "Delete from RC where Upper(RName)='" +name.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

        }
        catch (Exception e) {
            throw new myException(processSQLError(e));
        }
    }
    public void deleteRecipe(String currentRecipe) throws myException {
        String values;

        try
        {
            cmdString = "Delete from R where Upper(Name)='" +currentRecipe.toUpperCase()+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

        }
        catch (Exception e)
        {
            throw new myException(processSQLError(e));
        }
        deleteRecipeSteps(currentRecipe);
        deleteRecipeTags(currentRecipe);
        deleteNeed(currentRecipe);
    }

    public List<String> getFavoriteSequential() throws myException {
        Recipe recipe;
        List<String> favoriteResult = new ArrayList<String>();
        String myRecipeName;
        myRecipeName = EOF;

        try
        {
            cmdString = "Select * from F";
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
            cmdString = "Insert into F " +" Values('" +currentFavorite +"')";
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
                cmdString = "Select * from R Where Upper(Name) Like '%" + inputstr[0].toUpperCase() + "%'";
                rs2 = st1.executeQuery(cmdString);


                //ResultSetMetaData md2 = rs2.getMetaData();
            } catch (Exception e) {
                throw new myException(processSQLError(e));
            }
            if (rs2 != null) {
                try {
                    while (rs2.next()) {
                        myRecipeName = rs2.getString("Name");
                        result.add(myRecipeName);
                    }
                    rs2.close();
                } catch (Exception e) {
                    throw new myException(processSQLError(e));
                }
            }
        }
        else if(inputstr.length==2) {

           try {
             cmdString = "Select * from RC Where Upper(RName) Like '%" + inputstr[0].toUpperCase() + "%' AND Upper(CName)='"+inputstr[1].toUpperCase()+"'";
               rs2 = st1.executeQuery(cmdString);


               //ResultSetMetaData md2 = rs2.getMetaData();
           } catch (Exception e) {
               throw new myException(processSQLError(e));
           }
           if (rs2 != null) {
               try {
                   while (rs2.next()) {
                       myRecipeName = rs2.getString("RName");
                       result.add(myRecipeName);
                   }
                   rs2.close();
               } catch (Exception e) {
                   throw new myException(processSQLError(e));
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
}
