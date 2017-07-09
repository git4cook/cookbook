package com.groupd.cookbook.test.integrationTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by apple on 2017/7/8.
 */

public class IntegrationTest {


    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}
