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
public class Calculate {

    private Distance dist;

    /** Handles trip planning request, creating a new trip object from the trip request.
     * Does the conversion from Json to a Java class before planning the trip.
     * @param request
     */
    public Calculate (Request request) {
        // first print the request
        System.out.println(HTTP.echoRequest(request));

        // extract the information from the body of the request.
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());

        // convert the body of the request to a Java class.
        Gson gson = new Gson();
        dist = gson.fromJson(requestBody, Distance.class);

        // plan the trip.
        dist.findDist();

        // log something.
        System.out.println(dist.origin.name);
        System.out.println(dist.destination.name);
    }

    /** Handles the response for a Trip object.
     * Does the conversion from a Java class to a Json string.*
     */
    public String getDist() {
        Gson gson = new Gson();
        return gson.toJson(dist);
    }
}
