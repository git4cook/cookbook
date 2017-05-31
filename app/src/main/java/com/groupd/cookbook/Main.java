package com.groupd.cookbook;

/**
 * Created by yinyu on 2017/5/31.
 */

public class Main {
    public static final String dbNmae ="RE";

    public static void main(String[] args){
        startUp();

        CLI.run();

        shutDown();
        System.out.println("All done");



    }//main
    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        Services.closeDataAccess();
    }
}//Main
