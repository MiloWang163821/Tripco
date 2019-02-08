package com.tripco.t11.server;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Greeting class.
 */
@RunWith(JUnit4.class)
public class TestGreeting {
    Greeting greet;

    @Test
    public void testhtml() 
    {
        greet = new Greeting();
        String s = greet.html("hi mom!");
        assertTrue(s.length() > 25);
    }
}