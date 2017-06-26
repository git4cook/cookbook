package com.groupd.cookbook.application;


import com.groupd.cookbook.persistence.DataAccess;
import com.groupd.cookbook.persistence.DataAccessObj;
import com.groupd.cookbook.persistence.DataAccessStub;

public class Services
{
    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            //dataAccessService = new DataAccessObj(dbName);
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccessStub createDataAccess(DataAccessStub alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccessStub getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
