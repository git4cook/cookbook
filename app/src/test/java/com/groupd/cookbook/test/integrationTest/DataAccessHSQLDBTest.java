package com.groupd.cookbook.test.integrationTest;

import com.groupd.cookbook.application.Main;
import com.groupd.cookbook.application.Services;
import com.groupd.cookbook.persistence.DataAccess;
import com.groupd.cookbook.persistence.DataAccessObj;

import junit.framework.TestCase;

/**
 * Created by apple on 2017/7/8.
 */

public class DataAccessHSQLDBTest extends TestCase {
    private static String dbName = Main.dbName;

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccess dataAccess;
        try{

        }catch(Exception e){}
        try{
            Services.closeDataAccess();
        }catch(Exception e){}

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        // Use the following two statements to run with the real database
        try{
            Services.createDataAccess(dbName);
        }catch(Exception e){}

        try{
            dataAccess = Services.getDataAccess(dbName);
        }catch(Exception e){}
        //DataAccessTest.dataAccessTest();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}
