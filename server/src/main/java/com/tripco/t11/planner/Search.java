package com.tripco.t11.planner;
import java.util.ArrayList;
import java.util.List;


public class Search {

    public int version = 5;
    public String type = "search";
    public String match = "";
    public List<Filter> filters;
    public Integer limit = 0;
    public Integer found = 0;
    public ArrayList<Place> places;

    /**
     * This is a default constructor.
     */
    public Search(){
        this.match = null;
    }

    /**
     * This is a constructor.
     * @param match String compare match with places' names in the database.
     */
    public Search(String match){
        this.match = match;
    }

    /**
     * The top level method that does searching.
     */
    public void match(){
        SQLDatabase sql = new SQLDatabase();
        sql.connectSQL(this.match, this.limit, this.filters);
        this.places = (ArrayList<Place>) sql.places.clone();
        this.found = sql.found;
    }


}
