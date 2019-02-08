package com.tripco.t11.planner;
import java.lang.Math;

/**
 * Describes the places to visit in a trip in TFFI format.
 * There may be other attributes of a place, but these are required to plan a trip.
 */
public class Place {
  public String id;
  public String name;
  public String latitude;
  public String longitude;

  public Place(String id, String name, String latitude, String longitude){
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  //this is a purely test case, Place(num) is only to be used for testing
  public Place(int num){

    this.id = "rand";
    this.name = "random";
    this.latitude = "" + (Math.random()*1000-500);
    this.longitude = "" + (Math.random()*1000-500);

  }

}
