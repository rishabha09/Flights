package com.example.Flights.service;

import com.example.Flights.model.Flight;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CSVService {

  static public List<Flight> flights;

  public void readFlights(String filePath) throws FileNotFoundException {
    flights = new CsvToBeanBuilder<Flight>(new FileReader(filePath))
        .withType(Flight.class)
        .build()
        .parse();
  }

}
