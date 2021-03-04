package com.example.Flights.service.impl;

import com.example.Flights.constant.Constants;
import com.example.Flights.exception.ApplicationException;
import com.example.Flights.model.Flight;
import com.example.Flights.model.Response;
import com.example.Flights.service.CSVService;
import com.example.Flights.service.FlightService;
import com.example.Flights.util.HelperUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FlightServiceImpl implements FlightService {

  @Override
  public List<String> getFlightsBtwSrcAndDest(String src, String des) {

    List<Response> responses = getFlightsBySrcAndDest(src, des);

    List<String> flightsResult = new ArrayList<>();

    for (Response response : responses) {
      flightsResult.add( HelperUtil.getJsonResponse(response));
    }

    return flightsResult;
  }

  private List<Response> getFlightsBySrcAndDest(String src, String dest) {

    List<Response> responses = new ArrayList<>();

    List<Flight> srcFlights = getFlightsBySrc(src);
    List<Flight> desFlights = getFlightsByDes(dest);

    for(Flight flight : srcFlights) {
      if(flight.getDes().equals(dest)) {
        Response response = new Response();
        response.setCode(HelperUtil.getCode(Arrays.asList(flight)));
        response.setPath(HelperUtil.getPath(Arrays.asList(flight)));
        response.setDuration(HelperUtil.getDuration(Arrays.asList(flight)));
        responses.add(response);
        continue;
      }
      List<Flight> flights = desFlights.stream().filter( flt -> flt.getSrc().equals(flight.getDes())).collect(Collectors.toList());
      if(Objects.nonNull(flights)) {
        for(Flight flt : flights) {
          Response response = new Response();
          response.setCode(HelperUtil.getCode(Arrays.asList(flight, flt)));
          response.setPath(HelperUtil.getPath(Arrays.asList(flight, flt)));
          response.setDuration(HelperUtil.getDuration(Arrays.asList(flight, flt)));
          responses.add(response);
        }
      }
    }

    if(responses.size() == 0) {
      throw new ApplicationException(String.format("No flights found between %s and %s", src, dest));
    }

    // Sorting the response based on duration
    Collections.sort(responses, new Comparator<Response>() {
      @Override
      public int compare(Response o1, Response o2) {
        return o1.getDuration() - o2.getDuration();
      }
    });

    List<Response> result = responses.stream()
        .distinct()
        .collect(Collectors.toList());

    if(result.size() > Constants.No_OF_FLIGHTS_RETURN) {
      result = result.subList(0, Constants.No_OF_FLIGHTS_RETURN);
    }
    return result;
  }

  /**
   * fetch all the flights by src
   * @param src
   * @return
   */
  private List<Flight> getFlightsBySrc(String src) {
    List<Flight> srcFlights = CSVService.flights.stream().filter( flight -> flight.getSrc().equals(src)).collect(Collectors.toList());

    return srcFlights;
  }

  /**
   * fetch all the flights by dest
   * @param dest
   * @return
   */
  private List<Flight> getFlightsByDes(String dest) {
    List<Flight> desFlights = CSVService.flights.stream().filter( flight -> flight.getDes().equals(dest)).collect(Collectors.toList());

    return desFlights;
  }
}
