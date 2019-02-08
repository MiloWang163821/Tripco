package com.tripco.t11.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import java.lang.Math;

import static org.junit.Assert.*;

/*
  This class contains tests for the SQLDatabase class.
 */
@RunWith(JUnit4.class)
public class TestSQLDatabase {
  SQLDatabase sql;
  
  @Before
  public void initialze() {
    sql = new SQLDatabase();
  }

  @Test
  public void testSetLimit() {
    String a = sql.setLimit(5);
    assertTrue(a.equals("LIMIT 5"));

    String b = sql.setLimit(0);
    assertTrue(b.equals(""));

    String c = sql.setLimit(-5);
    String d = sql.setLimit(1500);
    
  }

  @Test
  public void testSetMatch() {
    String a = sql.setMatch("bah humbug");
    assertTrue(a.length() > 50);
    assertTrue(a.contains("OR"));
  }

  @Test
  public void testSetFilter1()
  {
    ArrayList<Filter> fil = new ArrayList<>(10);
    for (int k = 0; k < 10; k++)
    {
      Filter x = new Filter();
      x.name = "Name "+ Math.random();
      x.values = new ArrayList<String>(5);
      for (int l = 0; l < 5; l++)
      {
        x.values.add("Value"+Math.random());
      }
      fil.add(x);
      fil.add(new Filter("Empty", new ArrayList<>()));
    }
    
    String ret = sql.setFilter(fil);
    System.out.println("sql Filters: " + ret);
    assertTrue(ret.length() > 25);
  }

  @Test
  public void testSetFiltercleanup() {
    String a = sql.setFilter(null);
    
    String b = sql.setFilter(new ArrayList<Filter>());

    assertTrue(a.equals(b));

    ArrayList<Filter> fil = new ArrayList<>();
    ArrayList<String> values = new ArrayList<>();
    values.add("HI");
    fil.add(new Filter("continent", values ));
    fil.add(new Filter("type", values ));

    String c = sql.setFilter(fil);

    fil = new ArrayList<>();
    values = new ArrayList<>();
    fil.add(new Filter("hi", values));
    fil.add(new Filter("mom", values));

    String d = sql.setFilter(fil);
  }

  @Test
  public void testSetQueryStringcleanup() {
    String a = sql.setQueryString("", "hi mom");
    String b = sql.setQueryString("hi mom", "");
    String c = sql.setQueryString("hi", " mom");
    String d = sql.setQueryString("", "");
  }
}


