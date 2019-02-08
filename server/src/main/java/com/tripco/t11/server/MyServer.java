package com.tripco.t11.server;
import com.tripco.t11.planner.Option;
import com.tripco.t11.planner.Trip;
import com.tripco.t11.planner.Place;

import java.util.ArrayList;

/** The main class for the application.
 *
 * Command line arguments are of the form:
 * [port] [teaminfo]...
 *
 * Examples that produce the same result:
 * 31400 t11 Team Name Here
 * 31400 "t11 Team Name Here"
 */
public class MyServer {

  /** Main program starts a web microserver on the specified network port
   ** @param args command line arguments optionally containing port and team name.
   */
  public static void main(String[] args) {

    MicroServer server = new MicroServer(getPort(args), getName(args));

    for(int i = 0; i < 100; i++){

      Trip trip = new Trip();
      trip.places = new ArrayList<Place>();
      trip.options = new Option("miles","miles",3950,"shorter");
      for(int k = 0; k < 25; k++) {
        trip.places.add(new Place(3));
      }
      trip.optimize_concurrent();

      trip.places = new ArrayList<Place>();
      trip.options = new Option("miles","miles",3950,"shortest");
      for(int k = 0; k < 25; k++) {
        trip.places.add(new Place(3));
      }
      trip.optimize_concurrent();
    }

  }

  /** Obtain the port number from the command line arguments.  Defaults if none provided.
   * @param args
   * @return
   */
  private static int getPort(String[] args) {

    if (args.length > 0)
      return Integer.parseInt(args[0]);
    else
      return 8088; // some default

  }

  /** Obtain the name from the command line arguments.  Defaults if not specified.
   * @param args
   * @return a concatenation of the arguments after the port
   */
  private static String getName(String[] args) {

    if (args.length > 1) {
      String name = args[1];
      for (int i = 2; i < args.length; i++)
        name = name + " " + args[i];
      return name;
    }
    else
      return "Unknown";
  }

}