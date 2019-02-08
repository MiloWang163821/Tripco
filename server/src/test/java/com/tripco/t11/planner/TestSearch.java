package com.tripco.t11.planner;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestSearch{
    Search search;


    @Before
    public void initialize(){
        //search = new Search();
    }

    @Test
    public void searchInit(){
        search = new Search();
        assertTrue(search.match == null);

        search = new Search("howdy");
        assertTrue(search.match.equals("howdy"));
    }

    //@Test
    public void search(){
        assertTrue(true == true);
    }
}