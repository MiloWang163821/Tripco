package com.tripco.t11.server;

import com.google.gson.Gson;
import com.tripco.t11.planner.Opts;
import com.tripco.t11.planner.Filter;
import java.util.Arrays;
import java.util.List;

public class Config {

  private short version = 4;
  private String type = "config";

  private List<String> units = Arrays.asList("miles","kilometers","nautical miles","user defined");
  private List<Opts> optimization = Arrays.asList(new Opts("none","The trip is not optimized."),new Opts("short","Nearest neighbor."),new Opts("shorter","two Opt Optimization"),new Opts("shortest","three Opt Optimization"));
  private List<String> maps = Arrays.asList("svg","kml");
  private List<String> attributes = Arrays.asList("id","name","latitude","longitude","region","municipality","continent","country");

  private List<Filter> filters;


  public Config(){
        Filter filter1 = new Filter("world_airports.type", Arrays.asList("balloonport", "heliport", "small_airport", "medium_airport", "large_airport", "seaplane_base"));
        Filter filter2 = new Filter("continents.name", Arrays.asList("North America", "South America", "Africa", "Asia", "Oceania", "Europe", "Antarctica"));
        this.filters = Arrays.asList(filter1, filter2);
  }


  static String getConfig(){
    Config conf = new Config();
    Gson gson = new Gson();

    return gson.toJson(conf);
  }
}
