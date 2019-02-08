package com.tripco.t11.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestOpts
{
    Opts op;

    @Test
    public void testInit()
    {
        op = new Opts("your mother was a hamster", "your father smelled of elderberries");
        assertTrue(op.label.equals("your mother was a hamster") );
        assertTrue(op.description.equals("your father smelled of elderberries") );
    }
    

}