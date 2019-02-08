package com.tripco.t11.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t11.server.HTTP;
import spark.Request;

import java.util.ArrayList;

/**
 * This class handles to the conversions of the trip request/resopnse,
 * converting from the Json string in the request body to a Trip object,
 * planning the Trip, and
 * converting the resulting Trip object back to a Json string
 * so it may returned as the response.
 */
public class Plan {

  private Trip trip;

  public Plan()
  {
     //System.out.println("Creating a blank plan for testing");
  }

  /** Handles trip planning request, creating a new trip object from the trip request.
   * Does the conversion from Json to a Java class before planning the trip.
   * @param request
   */
  public Plan (Request request) {
    // first print the request
    // extract the information from the body of the request.

    JsonParser jsonParser = new JsonParser();
    JsonElement requestBody = jsonParser.parse(request.body());

    // convert the body of the request to a Java class.
    Gson gson = new Gson();
    trip = gson.fromJson(requestBody, Trip.class);

    // plan the trip.
    cleanTrip(trip);
    trip.plan();

    // log something.
    System.out.println(trip.title);
  }

  /** Handles the response for a Trip object.
   * Does the conversion from a Java class to a Json string.*
   */
  public String getTrip () {
    Gson gson = new Gson();
    String store = gson.toJson(trip);
   // String test = "\\";
   // while(test.contains("\\"))
   // {
   //     test = store.replace("\\", "");
   //     System.out.println("hi"); //infinite loop test
   // }
    //System.out.println(store);
    //System.out.println(trip.map);
    //System.out.println("Request, returned");

    return store;
  }

  public void cleanTrip(Trip jaunt){
    if(jaunt.options==null){
      jaunt.options = new Option("miles","miles",3959, "none");
    }
    //make the rather finicky options class behave
    if(jaunt.options.optimization == null){
      jaunt.options.optimization = "none";
    }

    //these are set to safe blank copies
    jaunt.distances = new ArrayList<Integer>();
    jaunt.map = "";//this should not be needed but just to be safe
    //System.out.println(jaunt.options.optimization);
  }

}
