package com.tripco.t11.planner;
import spark.Request;
import com.google.gson.*;
import org.json.*;



public class Match {

    private Search search;

    /** Handles places searching request, creating a new search object from the search request.
     * Does the conversion from Json to a Java class before searching the places.
     * @param request should be {a single string}.
     */
    public Match(Request request) {

        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());

        Gson gson = new Gson();
        search = gson.fromJson(requestBody, Search.class);

        // search the places.
        search.match();
    }

    /** Handles the response for a Search object.
     * Does the conversion from a Java class to a Json string.*
     */
    public String getMatch() {
        Gson gson = new Gson();
        String ret = gson.toJson(search);
       // System.out.println(ret);
        return ret;
    }
}