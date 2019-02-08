package com.tripco.t11.planner;

import java.util.*;

/**
 * contains two places and a distance
 * calculates the distance from the two places
 */
public class Distance {
    public Place origin;
    public Place destination;
    public int distance;
    public String units;

    private static Trip trip = null; //only need this for the get_earthradius

    public void findDist(){
        //formula here
        trip = new Trip();
        trip.options = new Option();
        trip.options.units = this.units; 
        
        this.distance = trip.calDist(origin, destination);
    }


}
