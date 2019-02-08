package com.tripco.t11.server;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Config class.
 */
@RunWith(JUnit4.class)
public class TestConfig {
    Config config;

    @Test
    public void test_getconfig()
    { 
        config = new Config();
        String c = config.getConfig();
        assertTrue(c.length() > 15);
    }
 
}