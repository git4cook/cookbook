package com.groupd.cookbook;


import com.groupd.cookbook.persistence.DataAccessStubTest;
import com.groupd.cookbook.objects.RecipeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DataAccessStubTest.class, RecipeTest.class})

/**
 * Created by Glenn on 2017-06-08.
 */

public class AllTests {
}
