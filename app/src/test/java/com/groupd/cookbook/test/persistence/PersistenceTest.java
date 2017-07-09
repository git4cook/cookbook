package com.groupd.cookbook.test.persistence;

/**
 * Created by apple on 2017/7/8.
 */
import junit.framework.Test;
import junit.framework.TestSuite;


public class PersistenceTest {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Persistence tests");
        suite.addTestSuite(DataAccessStubTest.class);
        return suite;
    }
}
