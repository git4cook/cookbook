package com.groupd.cookbook.application;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;


import com.groupd.cookbook.persistence.DataAccessStub;
import com.groupd.cookbook.presentation.Messages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by siyu on 2017/6/2.
 */


public class Main
{
    public static final String dbName = "SC";
    private static String dbPathName = "database/SC";
    public static void main(String[] args)
    {

        startUp();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
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
