package com.example.Flights.util;

import com.example.Flights.model.Flight;
import com.example.Flights.model.Response;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public class HelperUtil {

  public static String getJsonResponse(Response response) {
    JSONObject jsonObjectNested = new JSONObject();
    jsonObjectNested.put(response.getCode(), response.getDuration());
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(response.getPath(), jsonObjectNested);

    return jsonObject.toString();
  }

  public static String getCode(List<Flight> flights) {
    Set<String> codes = new LinkedHashSet<>();
    for(Flight flight : flights) {
      codes.add(flight.getCode());
    }

    return createCode(codes);
  }

  public static String createCode(Set<String> codes) {
    String path = "";
    for(String city : codes) {
      path += city + "_";
    }

    return path.substring(0, path.length()-1);
  }

  public static int getDuration(List<Flight> flights) {
    List<Integer> times = new LinkedList<>();
    for(Flight flight : flights) {
      times.add(flight.getDep());
      times.add(flight.getArr());
    }

    return createDuration(times);
  }

  public static int createDuration(List<Integer> times) {
    int duration = 0;
    for(int i=1; i<times.size(); i++) {

      long min1 = ((times.get(i-1)/100) * 60) + (times.get(i-1)%100);
      long min2 = ((times.get(i)/100) * 60) + (times.get(i)%100);

      duration += (min2 - min1);

      if(min2 < min1) {
        duration += 1440 ;
      }

    }
    return duration;
  }

  public static Boolean minHalt(int arr, int dep, long minHaltDurInMin) {
    long arrMin = (((arr/100) * 60) + (arr)%100);
    long depMin = (((dep/100) * 60) + (dep)%100);

    long dur = depMin - arrMin;
    if(dur < 0) { dur += 1440; }

    if(dur < minHaltDurInMin) {
      return false;
    }
    return true;
  }

  public static String getPath(List<Flight> flights) {
    Set<String> cities = new LinkedHashSet<>();
    for(Flight flight : flights) {
      cities.add(flight.getSrc());
      cities.add(flight.getDes());
    }
    return createPath(cities);
  }

  public static String createPath(Set<String> cities) {
    String path = "";
    for(String city : cities) {
      path += city + "_";
    }
    return path.substring(0, path.length()-1);
  }

}
