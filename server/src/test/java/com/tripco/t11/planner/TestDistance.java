package com.tripco.t11.planner;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Distance class.
 */
@RunWith(JUnit4.class)
public class TestDistance {
    Distance dist;

    // Setup to be done before every test in TestPlan
    @Before
    public void initialize() {
        dist = new Distance();
        Place p1 = new Place("dnvr", "denver", "39.742043", "-104.991531"); //denver
//        p1.latitude = "39.742043";
//        p1.longitude = "-104.991531";
        Place p2 = new Place("long", "longmont", "40.167206", "-105.101929"); //longmont
//        p2.latitude = "40.167206";
//        p2.longitude = "-105.101929";

        dist.origin = p1; dist.destination = p2; //set the places
        dist.units = "miles";
        
        //now we are ready to test
    }

    @Test
    public void testPlace1() {
        dist.findDist();
//        System.out.println(dist.distance + " This is the calculated distance");
        assertTrue(29 <= dist.distance && 31 >= dist.distance); //+-1 (true is 30)
//        assertTrue(dist.distance == 29);  //passes test, so this is actual calculated value as of 10/04
    }

    @Test
    public void testPlace2() {
        dist.units = "kilometers";
        dist.findDist();

        assertTrue(47 <= dist.distance && 49 >= dist.distance); //+-1 (true is 48)
    }

    //@Test
    //public void testPlace3() {
        //dist.units = "nautical miles";
        //dist.findDist();

//        assertTrue(25 <= dist.distance && 27 >= dist.distance); //+-1 (true is 26)
//
//    }

    @Test 
    public void testPlace4() {
        Place l1 = new Place("sh", "shanghai", "31.2304", "121.4737"); //shanghai
//        l1.latitude = "31.2304";
//        l1.longitude = "121.4737";
        Place l2 = new Place("sao", "sao paulo", "-23.5505", "-46.6333"); //sao paulo
//        l2.latitude = "-23.5505";
//        l2.longitude = "-46.6333";
        
        dist.origin = l1;
        dist.destination = l2;
        dist.units = "miles";

        dist.findDist();
        System.out.println(dist.distance + "mi from sao paulo to shanghai");
        assertTrue(11535 < dist.distance && 11543 >= dist.distance); // +-4 (true is 11359)


    }

}
