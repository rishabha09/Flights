package com.example.Flights.controller;

import com.example.Flights.constant.Routes;
import com.example.Flights.service.FlightService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

  @Autowired
  private FlightService flightService;

  /**
   * search the flights in asc duration order between src and dest
   * @param src
   * @param dest
   * @return
   */
  @GetMapping(Routes.searchFlights_BY_SRC_AND_DEST)
  public List<String> getParticipantDetailsByTypeAndId(@RequestParam(name = "src") String src,
      @RequestParam(name = "dest") String dest)
  {
    List<String> result = flightService.getFlightsBtwSrcAndDest(src, dest);

    return result;
  }

}
