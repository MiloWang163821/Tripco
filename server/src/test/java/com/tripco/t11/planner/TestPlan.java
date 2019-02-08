package com.tripco.t11.planner;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TestPlan
{
    Plan plan;
    Trip trip;

    @Before
    public void initialize()
    {
        plan = new Plan();
        trip = new Trip(); //a simulation of plan.trip
    }

    @Test
    public void testCleanTrip1()
    {
        plan.cleanTrip(trip);

        assertTrue(trip.options != null);
        assertTrue(trip.distances != null);
        assertTrue(trip.map != null);
    }

    @Test
    public void testCleanTrip2()
    {
        trip.options = new Option("feet", "feet", 2959000, "some");
        
        plan.cleanTrip(trip);

        assertTrue(trip.options.units.equals("feet"));
        assertTrue(trip.options.unitName.equals("feet"));
        assertTrue(trip.options.unitRadius == 2959000);
        assertTrue(trip.options.optimization.equals("some"));
    }

    @Test
    public void testCleanTrip3()
    {
        trip.options = new Option();
        trip.options.optimization = null;

        plan.cleanTrip(trip);
    }

    @Test
    public void testGetTrip()
    {
       // plan.trip = trip;

        plan.getTrip();
    }
}