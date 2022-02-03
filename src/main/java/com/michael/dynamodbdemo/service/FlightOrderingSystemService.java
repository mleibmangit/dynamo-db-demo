package com.michael.dynamodbdemo.service;

import com.michael.dynamodbdemo.controller.model.SearchFlightsRequest;
import com.michael.dynamodbdemo.model.*;
import com.michael.dynamodbdemo.repository.FlightOrderingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightOrderingSystemService {

    private final FlightOrderingSystemRepository flightOrderingSystemRepository;

    @Autowired
    public FlightOrderingSystemService(FlightOrderingSystemRepository flightOrderingSystemRepository) {
        this.flightOrderingSystemRepository = flightOrderingSystemRepository;
    }

    public String addPlane(Plane plane) {
        flightOrderingSystemRepository.addPlane(plane);
        return "";
    }

    public String addPassenger(Passenger passenger) {
        flightOrderingSystemRepository.addPassenger(passenger);
        return "";
    }

    public String addFlight(Flight flight) {
        flightOrderingSystemRepository.addFlight(flight);
        return "";
    }

    public List<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {
        return flightOrderingSystemRepository.searchFlights(searchFlightsRequest);
    }
}