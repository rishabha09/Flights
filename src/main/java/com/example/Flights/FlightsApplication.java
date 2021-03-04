package com.example.Flights;

import com.example.Flights.service.CSVService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsApplication.class, args);
		CSVService csvService = new CSVService();
		try {
			csvService.readFlights("/home/delhivery/Downloads/Flights/src/main/resources/xx-sched.csv");
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
