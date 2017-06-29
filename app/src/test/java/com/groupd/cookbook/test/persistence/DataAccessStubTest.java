package com.groupd.cookbook.test.persistence;

import com.groupd.cookbook.objects.Recipe;
import com.groupd.cookbook.objects.tag;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class DataAccessStubTest {
    DataAccessStub test;

    @Before
    public void setUp() {test = new DataAccessStub("testDB");}

    @After
    public void tearDown() {test = null;}
    @Test
    public void stubInsert() {

    }

}