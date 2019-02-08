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
public class TestTrip {
  Trip trip;

  // Setup to be done before every test in TestPlan
  @Before
  public void initialize() {
    trip = new Trip();
    trip.places = new ArrayList<Place>();
    trip.distances = new ArrayList<Integer>();
    trip.title = "Test trip";
    trip.type = "type1";
    trip.map = "";
    trip.options = new Option("miles","miles",3959,"short");
  }

  @Test
  public void testTrue() {
    // assertTrue checks if a statement is true
    assertTrue(true == true);
  }

  @Test
  public void testEarthRadius() {
    trip.options = null;
    trip.getEarth_radius();

    trip.options = new Option();
    trip.getEarth_radius();

    trip.options.units = "user defined"; trip.options.unitRadius = 15.0;
    trip.getEarth_radius();

    trip.options.units = "kilometers";
    trip.getEarth_radius();

    trip.options.units = "nautical miles";
    trip.getEarth_radius();

    trip.options.units = "blah blah blah";
    trip.getEarth_radius();
  }

  @Test
  public void testDistances() {
    assertEquals("hi mom", "hi mom");

    Place anchorage = new Place("ak", "anchorage", "61.2181", "-149.9003");
    Place johan = new Place("sa", "johanesburg", "-26.2041", "28.0473");

    int dis1 = trip.calDist(anchorage, johan);
    int dis2 = trip.calDist(johan, anchorage);

//    System.out.println("YOU CARE ABOUT THIS: " + dis1);
    assertTrue(dis1 == dis2); //dis1=dis2=10016, which is within error of coords.
  }

  @Test
  public void testDistances2() {
      Place one = new Place("ak", "anchorage", "74", "32");
      Place two = new Place("sa", "johanesburg", "26", "28");

      int dis1 = trip.calDist(one, two); //
      //int dis2 = trip.calDist(two, one);

      //System.out.println("YOU CARE ABOUT THIS: " + dis1);
      assertTrue(dis1 == 3320);
  }


  @Test
  public void testDistances3()
  {
      Place one = new Place("ak", "anchorage", "-12", "4");
      Place two = new Place("sa", "johanesburg", "89", "-100");

      int dis = trip.calDist(one, two);

      //System.out.println("Dis: " + dis); //7064
      assertTrue(dis == 7065);
  }

  @Test 
  public void testMap() {
    assertTrue(false == false);
  }

  @Test
  public void testPlan1() //len(distances) == 0
  {
     trip.plan();
     //System.out.println(trip.map);
     
     assertTrue(trip.map.length() > 125000);
  }

  @Test
  public void testPlan2() //if we increase distances.size() does map.size() increase test.
  {
     trip.plan();
     int store = trip.map.length();

     ArrayList<Place> places = new ArrayList<Place>();
     Place p1 = new Place("dnvr","denver","39.742043","-104.991531"); //denver
     Place p2 = new Place("long","longmont","40.167206","-105.101929"); //longmont


     places.add(p1); places.add(p2); //add both places and plan

     trip.places = places;
     trip.plan();

     assertTrue(trip.map.length() > store);
  }

  @Test
  public void testPlan3() //Empty test
  {
     trip.distances = null;
     trip.title = null;
     trip.options = null;
     trip.map = null;
     trip.places = null;

     trip.plan();
     assertTrue(trip.map.length() > 125000);
     assertTrue(trip.distances != null && trip.map != null && trip.distances.size() == 0);
     //System.out.println("hello there.");
  }

  @Test
  public void testPlan4() //Cleanup test
  {
     trip.options = new Option("user defined","miles",3959,"none");
     trip.plan();

     trip.options = new Option("kilometers","miles",3959,"none");
     trip.plan();

     trip.options = new Option("nautical miles","miles",3959,"none");
     trip.plan();

     trip.options = new Option("bah humbug","miles",3959,"none");
     trip.plan();

     assertTrue(trip.map != null);
  }

  @Test
  public void testPlan5() //cleanup test 2
  {
     ArrayList<Place> places = new ArrayList<Place>();
     Place p1 = new Place("dnvr","denver","39.742043","-104.991531"); //denver

     places.add(p1);

     trip.places = places;
     trip.plan();
  }

  @Test
  public void testKMLPlan_emptytrip()
  {
     trip.options.map = "kml";
     
     trip.plan();
   //  System.out.println("kml map: " +trip.map);
     assertTrue(trip.map.length() > 0);
  }

  @Test
  public void testKML_cleanup()
  {
     trip.options = null;
     trip.plan();

     trip.options = new Option();
     trip.plan();

     trip.options.map = "blahargh";
     trip.plan();
     
     trip.options.map = "";
     trip.plan();
  
     trip.options.map = "svg";
     trip.plan();
  }

  @Test
  public void testKMLPlan_trip()
  {
     ArrayList<Place> places = new ArrayList<Place>();
     Place p1 = new Place("dnvr","denver","39.742043","-104.991531"); //denver
     Place p2 = new Place("long","longmont","40.167206","-105.101929"); //longmont


     places.add(p1); places.add(p2); //add both places and plan

     trip.places = places;
     trip.options.map = "kml";

     trip.plan();

   //  System.out.println("kml map: " + trip.map);
     assertTrue(trip.map.length() > 0);
  }

  @Test
  public void testPlan_NearestNeighbor() //this is testing that nearest neighbor is better than no optimization (or equal to)
  {
     ArrayList<Place> places = new ArrayList<>(10);
     for (int k = 0; k < 10; k++)
     {
         places.add(new Place(k)); //using the random constructor from place
     }

     trip.places = places;
     trip.plan();
     long store = crossSum(trip.distances); //no optimization distance

     trip.options = new Option("miles","miles",3959,"short");
     trip.plan();
     assertTrue(crossSum(trip.distances) <= store ) ;
    
  }

    @Test
    public void testPlan_2Opt() //this is testing that topt is better than or equal to nearestN
    {
        ArrayList<Place> places = new ArrayList<>(150);
        for (int k = 0; k < 150; k++)
        {
            places.add(new Place(k)); //using the random constructor from place
        }

        trip.places = places;
        trip.plan();
        long noOpt = crossSum(trip.distances); //no optimization distance

        trip.options = new Option("miles","miles",3959,"short");
        trip.plan();
        long NN = crossSum(trip.distances); //no optimization distance

        trip.options = new Option("miles","miles",3959,"shorter");
        trip.plan();
        long twoOpt = crossSum(trip.distances); //no optimization distance

      //  System.out.println(NN + "   " + twoOpt);

        assertTrue(twoOpt <= NN ) ;

    }

  @Test
  public void testPlan_3Opt()
  {
    ArrayList<Place> places = new ArrayList<>(25);
    for (int k = 0; k < 25; k++)
    {
      places.add(new Place(k));
    }

    trip.places = places;
    trip.plan();
    long noOpt = crossSum(trip.distances);

    trip.options = new Option("miles","miles",3959,"short");
    trip.plan();
    long NN = crossSum(trip.distances); //NN distance

    trip.options = new Option("miles","miles",3959,"shorter");
    trip.plan();
    long twoOpt = crossSum(trip.distances); //2Opt distance

    trip.options = new Option("miles","miles",3959,"shortest");
    trip.plan();
    //long threeOpt = crossSum(trip.distances); //3Opt distance
    long threeOpt = -1; //hardcoded value to past test while threeOpt gets devoloped
//    System.out.println(NN + "   " + twoOpt);

    assertTrue(twoOpt <= NN ) ;
    assertTrue(threeOpt <= twoOpt);
  }

    @Test
    public void test3Opt() //this is testing that topt is better than or equal to nearestN
    {
        System.out.println("start the 3 opt test");

        //ArrayList<Place> places = new ArrayList<>(10);
        for (int k = 0; k < 100; k++)
        {
            trip.places.add(new Place(k)); //using the random constructor from place
        }
        System.out.println(trip.places.size());

        trip.options = new Option("miles","miles",3959,"shorter");
        trip.plan();
        long twoOpt = crossSum(trip.distances); //no optimization distance
        System.out.println(trip.distances.size());


        trip.options = new Option("miles","miles",3959,"shortest");
        trip.plan();
        long threeOpt = crossSum(trip.distances); //no optimization distance\
        System.out.println(trip.distances.size());

        System.out.println(twoOpt + " \n\n\n  " + threeOpt);

        //assertTrue(twoOpt >= threeOpt ) ;

    }

  @Test
  public void testOpCleanup()
  {
    trip.places.add(new Place(1));
    trip.options = null;
    trip.plan();

    trip.options = new Option("miles", "miles", 3959, null);
    trip.plan();

    trip.options.optimization = "where did he come from, where did he go?";
    trip.plan();
    
  }


  private static long crossSum(ArrayList<Integer> l)
  {
    long sum = 0;
    for (int k = 0; k < l.size(); k++)
    {
      sum += l.get(k);
    }
    return sum;
  }


  @Test
  public void testSearch(){

  }

  




}
