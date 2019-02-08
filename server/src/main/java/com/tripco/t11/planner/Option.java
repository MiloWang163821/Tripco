package com.tripco.t11.planner;

/**
 * Describes the options to apply when planning a trip in TFFI format.
 * At this point we are only using the values provided.
 */
public class Option {

  public String units;
  public String unitName;
  public Double unitRadius;
  public String optimization;
  public String map;

  public Option(String units,String unitName,double unitRadius,String optimization){
    this.units = units;
    this.unitName = unitName;
    this.unitRadius = unitRadius;
    this.optimization = optimization;
  }

  public Option(String units,String unitName,double unitRadius,String optimization, String map){
    this.units = units;
    this.unitName = unitName;
    this.unitRadius = unitRadius;
    this.optimization = optimization;
    this.map = map;
  }
  
  public Option(){

  }

}
