package com.tripco.t11.server;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the MicroServer class.
 */
@RunWith(JUnit4.class)
public class TestMicroServer
{
    MicroServer micro;

    @Test
    public void testInit()
    {
        micro = new MicroServer(8088, "The Hobbytes");
        assertTrue(micro != null);
    }
}