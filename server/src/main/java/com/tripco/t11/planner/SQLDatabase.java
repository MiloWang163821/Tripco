package com.tripco.t11.planner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class SQLDatabase {

    // db configuration information
    private final static String myDriver = "com.mysql.jdbc.Driver";
    private final static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    private final static String user="cs314-db";
    private final static String pass="eiK5liet1uej";

    // fill in SQL queries to count the number of records and to retrieve the data
    private static String count = "";
    private static String search = "";
    public static int found = 0;
    public static ArrayList<Place> places = new ArrayList<>();


    private static void printJSON(ResultSet count, ResultSet query, String match) throws SQLException {
        places.clear();
        found = 0;
        // determine the number of results that match the query
        count.next();
        int results = count.getInt(1);
        // iterate through query results and print out the airport codes
        while (query.next()) {
            final Place place = new Place(query.getString("id"),
                    query.getString("name"),
                    query.getString("latitude"),
                    query.getString("longitude"));
            places.add(place);
            found++;
        }
        System.out.printf(" ]\n}\n");
    }

    public static String setLimit(int limit){
        if(limit < 0){
            limit = 1000;
        }
        else if(limit == 0){
            return "";
        }
        else if (limit > 1000) {
             limit = 1000;
        }
        String mylimit = "LIMIT " + limit;
        return mylimit;
    }

    public static String setMatch(String match){
        String myMatch = "country.name LIKE '%" + match + "%' " + "OR world_airports.municipality LIKE'%" + match + "%' " +
                        "OR world_airports.name LIKE '%" + match + "%' " +
                        "OR world_airports.id LIKE '%" + match + "%' ";
        return myMatch;
    }

    public static String setFilter(List<Filter> filters){
        String myFilters = "";
        if (filters == null || filters.size() == 0){
            return myFilters ;
        }
        System.out.println("size: " + filters.size());
       
        for (int i = 0; i < filters.size(); i++) {
            if(filters.get(i).values.size()!=0){
                Filter filter = filters.get(i);
                String filterString = "";

                String confName = "";
                if(filter.name.equals("continent")){
                    confName = "continents.name";
                }
                else if(filter.name.equals("type")){
                    confName = "world_airports.type";
                }
                else{
                    confName = filter.name;
                }
                
                filterString += confName + " IN (";
                for (int j = 0; j < filter.values.size(); j++) {
                    filterString += "\"" + filter.values.get(j) + "\", ";
                }
                filterString = filterString.substring(0, filterString.length() - 2) + ")";
                myFilters += filterString + " AND ";
            }
        }
        if (myFilters.length() > 4) {
            myFilters = myFilters.substring(0, myFilters.length() - 4); 
            //whitescreen glitch here, myFilters.length() == 0, hopefully patched with the if 
        }
        return myFilters;
    }

    public static String setQueryString(String myMatch, String myFilter){
        String myQuery = "";

        if (myMatch.equals("") && !myFilter.equals("")){
            myQuery = "WHERE " + myFilter;
        } else if (myFilter.equals("") && !myMatch.equals("")){
            myQuery = "WHERE " + myMatch + " ";
        } else if (myFilter.equals("") && myMatch.equals("")){
            myQuery = " ";
        } else {
            myQuery = "WHERE ( " + myMatch + " ) AND " + myFilter;
        }

        return myQuery;
    }



    public static void connectSQL(String match, int limit, List<Filter> filters){
        String myLimit = setLimit(limit);
        String myMatch = setMatch(match);
        String myFilter = setFilter(filters);
        String myQuery = setQueryString(myMatch, myFilter);

        
        count = "SELECT count(*) FROM world_airports";
        search = "SELECT world_airports.id, world_airports.name, world_airports.municipality, world_airports.latitude, world_airports.longitude, country.name, continents.name, world_airports.type, region.name " + 
                "FROM continents " + 
                "INNER JOIN country ON continents.id = country.continent " +
                "INNER JOIN region ON country.id = region.iso_country " +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region "
                + myQuery +
                " ORDER BY continents.name, country.name, region.name, world_airports.name ASC "
                + myLimit + ";";

        System.out.println(search);
                
        try {
            Class.forName(myDriver);
            // connect to the database and query
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search)
            ) {
                printJSON(rsCount, rsQuery, match);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        search = "SELECT world_airports.id, world_airports.name, world_airports.municipality, world_airports.latitude, world_airports.longitude, country.name, continents.name, world_airports.type, region.name " + 
                "FROM continents " + 
                "INNER JOIN country ON continents.id = country.continent " +
                "INNER JOIN region ON country.id = region.iso_country " +
                "INNER JOIN world_airports ON region.id = world_airports.iso_region "
                + myQuery +
                " ORDER BY continents.name, country.name, region.name, world_airports.name ASC;";
        found = 0;
        try {
            Class.forName(myDriver);
            // connect to the database and query
            try (Connection conn = DriverManager.getConnection(myUrl, user, pass);
                 Statement stCount = conn.createStatement();
                 Statement stQuery = conn.createStatement();
                 ResultSet rsCount = stCount.executeQuery(count);
                 ResultSet rsQuery = stQuery.executeQuery(search)
            ) {
                while(rsQuery.next()){
                    found++;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }


}

