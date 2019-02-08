package com.tripco.t11.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t11.server.HTTP;
import com.tripco.t11.planner.Optimization;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.lang.Class;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import spark.Request;


/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 *
 */
public class Trip {
  // The variables in this class should reflect TFFI.
  public Integer version;
  public String type;
  public String title;
  public Option options;
  public ArrayList<Place> places;
  public ArrayList<Integer> distances;
  public String map;




  /** The top level method that does planning.
   * At this point it just adds the map and distances for the places in order.
   * It might need to reorder the places in the future.
   */
  public void plan() {
    long start = System.currentTimeMillis();
    optimize_concurrent();
    System.out.println("it took :"+(System.currentTimeMillis() - start)+"ms");
    this.map = mapping();
    this.distances = legDistances();
    System.out.println(crossSum(this.distances));
  }

  private String mapping()
  {
    if ( this.options == null || this.options.map == null || this.options.map.equals("svg"))
    {
       return svg();
    }
    else if (this.options.map.equals("kml"))
    {
       return kml();
    }
    return svg();
  }

  /**
  * Returns a kml that contains the legs of the trip
  * combined with google's api, this will map the trip.
  */
  private String kml(){
    String t = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
    t += "  <Document>\n";
    for (int k = 0; k < this.places.size(); k++)
    {
      Place cur = this.places.get(k);
      String e = "  <Placemark>\n    <name>"+cur.name.replace("&", "and")+"</name>\n";
      e += "    <Point>\n      <coordinates>"+cur.longitude+","+cur.latitude+"</coordinates>\n    </Point>\n";
      e += "  </Placemark>\n";

      t += e;
    }

    if (this.places.size() > 0)
    {

       t += "  <Placemark>\n    <LineString>\n      <coordinates>\n";
       for (int j = 0; j < this.places.size(); j++)
       {
         Place cur = this.places.get(j);
         t += "        "+cur.longitude+","+cur.latitude+"\n";
       }
       t +=   "        "+this.places.get(0).longitude+","+this.places.get(0).latitude+"\n";
       t += "      </coordinates>\n    </LineString>\n ";
       t += "    <Style>\n      <LineStyle>\n        <color>#ffff0000</color>\n      </LineStyle>\n    </Style>\n";
       t += "  </Placemark>\n";
    }
    t += "  </Document>\n</kml>";
    
    return t;
  }

  /**
   * Returns an SVG containing the background and the legs of the trip.
   * @return a string that should map the world as well as the trip.
   */
  private String svg() {
    this.map = getFreshMap();

    //not enough places to draw lines, just return the image
    if(places == null || places.size() <= 1) 
    {  //maybe later, return a dot on the single location?
        return map;
    }

    String lines = "";
    for(int k = 0; k< places.size(); k++)
    {
         lines += drawLines(k, (k+1) % places.size());
    }

    //System.out.println(lines);

    map = new StringBuffer(map).insert(map.length() - 6, lines).toString(); //It might be faster to have the client have this map natively,
    //and just pass the changes instead of passing the entire map each and every time.

    return map;
  }

  private String drawLines(int index, int next)
  {
    String line = "";
    double p1long = Double.parseDouble(places.get(index).longitude);
    double p2long = Double.parseDouble(places.get(next).longitude);
    
    int y1 = transformlati(places.get(index).latitude);
    int x1 = transformlong(places.get(index).longitude);

    int y2 = transformlati(places.get(next).latitude);
    int x2 = transformlong(places.get(next).longitude);

    double dis = Math.abs(p1long - p2long);
    if (dis > 180.0)
    {
      if (p2long < 0)
      {
        line += "  <line x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+(x2+1024)+"\" y2=\""+y2+"\" style=\"stroke:rgb(0,0,255);stroke-width:2\" />\n";
        line += "  <line x1=\""+(x1-1024)+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\" style=\"stroke:rgb(0,0,255);stroke-width:2\" />\n";
      }
      else
      {
        //System.out.println("hi");
        line += "  <line x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+(x2-1024)+"\" y2=\""+y2+"\" style=\"stroke:rgb(0,0,255);stroke-width:2\" />\n";
        line += "  <line x1=\""+(x1+1024)+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\" style=\"stroke:rgb(0,0,255);stroke-width:2\" />\n";
      }
    }
    else
    {
      line = "  <line x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\" style=\"stroke:rgb(0,0,255);stroke-width:2\" />\n";
    }

    return line;
  }

  private static int transformlong(String longitude)
  {
    //if (longitude.matches("\\d{2}[°]\\d{2}[\"]\\d+[.]\\d+[']")
    double l = -1.0;
    try
    {
        l = Double.parseDouble(longitude);
    }
    catch(NumberFormatException e)
    {
        //for now, do nothing, but longitude is in the form dd°dd"dd.dddd', l = degrees + (minutes/60) + (seconds/3600)
        return -1;
    }

    l = (l*(2.8456)) + 512;
    //this line transforms the longitude to svg values for Colorado
    //This assumes a perfectly flat, rectangular Colorado, therefore there is some error. Although it is very fast.

    return (int)l;
  }

  private static int transformlati(String latitude)
  {
    double l = -1.0;
    try
    {
        l = Double.parseDouble(latitude);
    }
    catch(NumberFormatException e)
    {
        //for now, do nothing, but longitude is in the form dd°dd"dd.dddd', l = degrees + (minutes/60) + (seconds/3600)
        return -1;
    }

     l = (l*(-2.825962)) + 256; //this line transforms the longitude to svg values for Colorado
    //This assumes a perfectly flat, rectangular Colorado, therefore there is some error. Although it is very fast.

    return (int)l;
  }

  /*This function returns the pixel size of the svg
  * Right now it is hardcoded until a better solution is found
  */
  private static int svghorizontalsize()
  {
    return 1067; //this is a hardcoded value pulled from CObackground.svg
  }

  /*This function returns the pixel size of the svg
  * Right now it is hardcoded until a better solution is found
  */
  private static int svgverticalsize()
  {
    return 783; //this is a hardcoded value pulled from CObackground.svg
  }

  private String getFreshMap()
  {
    BufferedReader read;
    try
    {
      InputStreamReader temp = new InputStreamReader(getClass().getResourceAsStream("/World_map_with_nations.svg"));
      read = new BufferedReader(temp);
    }
    catch (Exception e)
    { 
      System.err.println("Couldn't find the background map");
      return "        "; 
    }

    String line = "";
    String total = "";
    
    try 
    {
       while (( line = read.readLine() )!= null)
       {
         total += line;
       }
    }
    catch (Exception e)
    {
       System.err.println("readLine failed ");
       //do nothing...
    }
 
    //System.out.println("total: " +total);

    return total;
    
  }

  /**
  * This method returns the radius of the earth in the given units.
  * units comes from this.options.units
  * @return radius of earth as a double
  */
  public double getEarth_radius(){
    if(options == null){
      return 3959; } //this is probably a test case, just default to miles
    else if("user defined".equals(options.units)) {
      return options.unitRadius; }
    else if("kilometers".equals(options.units)) {
      return 6371; }
    else if("nautical miles".equals(options.units)) {
      return 3440; }
    //default to miles
    return 3959;
  }

  /**
  * this method takes two places and returns the vincenty great circle distance between them
  * @param origin, the first place
  * @param destination, the second place
  *
  * @return the distance between them as an int
  */
  public int calDist(Place origin, Place destination)
  {
    double lamda1 = Math.toRadians(Double.valueOf(origin.longitude)); //lambda1
    double theta1 = Math.toRadians(Double.valueOf(origin.latitude)); //theta1
    double lamda2 = Math.toRadians(Double.valueOf(destination.longitude));
    double theta2 = Math.toRadians(Double.valueOf(destination.latitude));

    double deltalamda = Math.abs(lamda1 - lamda2);

    double a = Math.pow(Math.cos(theta2)*Math.sin(deltalamda), 2);
    double b = Math.pow( (Math.cos(theta1)*Math.sin(theta2)) - (Math.sin(theta1)*Math.cos(theta2)*Math.cos(deltalamda)), 2);
    double c = (Math.sin(theta1)*Math.sin(theta2)) + (Math.cos(theta1)*Math.cos(theta2)*Math.cos(deltalamda));

    double top = Math.sqrt(a + b);
    double bottom = c;

    double finished = Math.atan2(top, bottom);
    //System.out.println("Data:" + finished + " " + a + " " + b + " " + c);
    int distance = (int)Math.round(finished * getEarth_radius());
    return distance;
  }

  /**
   * Returns the distances between consecutive places,
   * including the return to the starting point to make a round trip.
   * @return the arraylist of distances between member variable places
   *     where the nth term is the distance between the n and n+1 th term in places
   */
  private ArrayList<Integer> legDistances() {
    ArrayList<Integer> dist = new ArrayList<Integer>();
    if (places == null || places.size() == 0) //some base cases, dont try anything when size is 1 or 0
    {
      return new ArrayList<Integer>();
    }
    else if (places.size() == 1)
    {
      dist.add(0);
      return dist;
    }


    for(int i = 0; i < places.size()-1; i++){
      int d = calDist(places.get(i), places.get(i+1));
      dist.add(d);
    }
    int tmp = calDist(places.get(0), places.get(places.size()-1));
    dist.add(tmp);
    
    return dist;
  }


  /**
  * this function calls the correct optimization function to optimize the trip
  *//*
  public void optimize(){
    if( places == null || places.size() == 0){
      return;
    }
    if(options == null || options.optimization == null){
      return;
    }
    int[][] distT = distTable();

    if("short".equals(options.optimization)){
      nearestN(distT);
    }
    else if("shorter".equals(options.optimization)){
      twoOpt(distT);
    }
    else if("shortest".equals(options.optimization)){
        //System.out.println("starts 3Opt");
      threeOpt(distT);
    } 

  } */

  public void optimize_concurrent(){
    if( places == null || places.size() == 0){
      return;
    }
    if(options == null || options.optimization == null){
      return;
    }
    int[][] distT = distTable();

    //make the list of optimizations
    ArrayList<Optimization> allOpts = new ArrayList<>();
    for(int i = 0; i < places.size();i++){
      Optimization O = new Optimization(distT,i,options.optimization);
      allOpts.add(O);
    }

    //start the threads
    for(int i = 0; i < places.size();i++){
      allOpts.get(i).start();
    }

    //wait for the threads to finish
    for(int i = 0; i < allOpts.size();i++){//should ensure that all the optimizations have been done
      try {
        allOpts.get(i).join();
      }catch (Exception ex){
        System.err.println("the try catch in optimize got called on join");
        allOpts.remove(i);
      }
    }
    //System.out.println("master is continuing");

    //find the best optimization
    int holder = allOpts.get(0).totalDist;
    int best = 0;
    for(int i = 1; i < places.size();i++){
      if(allOpts.get(i).totalDist < holder){
        best = i;
        holder = allOpts.get(i).totalDist;
      }
    }

    //use the best optimization to update the places list
    ArrayList<Place> newPlaces = new ArrayList();
    for(int i = 0; i < places.size() ;i++){
      newPlaces.add(places.get(allOpts.get(best).results[i]));
    }
    places = newPlaces;


  }

  //build a distTable
  private int[][] distTable(){
    int[][] hold = new int[places.size()][places.size()];
    int temp;
    for(int i = 0; i < places.size();i++){
      for(int k = i; k < places.size();k++) {
        temp = calDist(places.get(i), places.get(k));
        hold[i][k] = temp;
        hold[k][i] = temp; //can cut work in half but am too lazy
      }
    }

    return hold;
  }

  private long crossSum(ArrayList<Integer> l)
  {
      long d = 0;
      for (int k = 0; k < l.size(); k++)
      {
          d += l.get(k);
      }
      return d;
  } 


}
