package com.groupd.cookbook.test;

/**
 * Created by Glenn on 2017-07-18.
 */

import com.groupd.cookbook.test.*;
import com.groupd.cookbook.test.integrationTest.BusinessPersistenceSeamTest;
import com.groupd.cookbook.test.integrationTest.DataAccessHSQLDBTest;
import com.groupd.cookbook.test.integrationTest.IntegrationTest;
import com.groupd.cookbook.test.objects.TagTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TagTest.class, BusinessPersistenceSeamTest.class, DataAccessHSQLDBTest.class})


public class AllTests {
}
