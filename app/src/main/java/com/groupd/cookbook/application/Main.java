package com.groupd.cookbook.application;

import com.groupd.cookbook.objects.myException;

/**
 * Created by siyu on 2017/6/2.
 */


public class Main
{
    public static final String dbName = "SC";
    private static String dbPathName = "database/SC";
    public static void main(String[] args) throws myException {

        startUp();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp() throws myException {
        Services.createDataAccess(dbName);
    }

    public static void shutDown() throws myException {
        Services.closeDataAccess();
    }
    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }



}
