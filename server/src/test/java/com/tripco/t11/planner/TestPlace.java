package com.tripco.t11.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestPlace {
  Place place;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    place = new Place("dnvr", "Denver", "39.742043", "-105.101929");

  }

  @Test 
  public void testPlace1() {
    String id = "dnvr";
    String name = "Denver";
    assertEquals(place.id, id);
    assertEquals(place.name,name);
  }

  @Test
  public void testPlace2() {
     String latitude = "39.742043";
     String longitude = "-105.101929";
     assertEquals(place.latitude, latitude);
     assertEquals(place.longitude, longitude); 
  }

  @Test
  public void testPlaceRand() {
    place = new Place(7); //using dummy constructor to create a random location
    assertTrue(place.id.equals("rand") );
    assertTrue(place.name.equals("random"));
    double lat = Double.parseDouble(place.latitude);
    assertTrue(lat < 1000 && lat > -1000);
    double lo = Double.parseDouble(place.longitude);
    assertTrue(lo < 1000 && lo > -1000);
  }

}
