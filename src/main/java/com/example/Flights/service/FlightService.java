package com.example.Flights.service;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface FlightService {

  List<String> getFlightsBtwSrcAndDest(String src, String des);

}
