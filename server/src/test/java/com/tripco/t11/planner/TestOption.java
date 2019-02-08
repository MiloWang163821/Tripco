package com.tripco.t11.planner;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestOption
{
    Option op;

    @Test
    public void testInit() 
    {
        op = new Option("miles", "miles", 3595.0,"none");
        assertTrue(op.units.equals("miles"));
        assertTrue(op.unitName.equals("miles"));
        assertTrue(op.unitRadius == 3595.0);
        assertTrue(op.optimization.equals("none"));
    }

    @Test
    public void testDefaultInit()
    {
        op = new Option();
        assertTrue(op.units == null);
        assertTrue(op.unitName == null);
        assertTrue(op.optimization == null);
    }

    @Test
    public void testInit2() 
    {
        op = new Option("miles", "miles", 3959, "none", "svg");
    }
}