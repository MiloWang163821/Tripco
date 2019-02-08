package com.tripco.t11.planner;

import java.util.List;

public class Filter {

    public String name;
    public List<String> values;

    public Filter(){}

    public Filter(String name, List<String> values){
        this.name = name;
        this.values = values;
    }


}
