package com.michael.dynamodbdemo.controller;

import com.michael.dynamodbdemo.controller.model.CreatedObjectResponse;
import com.michael.dynamodbdemo.model.*;
import com.michael.dynamodbdemo.repository.FlightOrderingSystemRepository;
import com.michael.dynamodbdemo.service.FlightOrderingSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping
@RestController
public class FlightOrderingSystemController {

    private final FlightOrderingSystemService flightOrderingSystemService;
    private final FlightOrderingSystemRepository flightOrderingSystemRepository;

    @Autowired
    public FlightOrderingSystemController(FlightOrderingSystemService flightOrderingSystemService,
                                          FlightOrderingSystemRepository flightOrderingSystemRepository) {
        this.flightOrderingSystemService = flightOrderingSystemService;
        this.flightOrderingSystemRepository = flightOrderingSystemRepository;
    }

    @GetMapping(value = "test")
    public void addPupil() {
        flightOrderingSystemRepository.test();
    }

    @PostMapping(value = "addPlane", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjectResponse> addPlane(@RequestBody Plane plane) {
        log.debug("got addPlane request {}", plane);
        return ResponseEntity.ok(new CreatedObjectResponse(flightOrderingSystemService.addPlane(plane), CreatedObjectResponse.ObjectType.PLANE));
    }

    @PostMapping(value = "addPassenger", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjectResponse> addPassenger(@RequestBody Passenger passenger) {
        log.debug("got addPassenger request {}", passenger);
        return ResponseEntity.ok(new CreatedObjectResponse(flightOrderingSystemService.addPassenger(passenger), CreatedObjectResponse.ObjectType.PASSENGER));
    }

    @PostMapping(value = "addFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjectResponse> addFlight(@RequestBody Flight flight) {
        log.debug("got addFlight request {}", flight);
        return ResponseEntity.ok(new CreatedObjectResponse(flightOrderingSystemService.addFlight(flight), CreatedObjectResponse.ObjectType.FLIGHT));
    }

    @PostMapping(value = "order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> orderBestFlight(@RequestBody DesiredFlight desiredFlight) {
        log.debug("got orderBestFlight request {}", desiredFlight);
        return ResponseEntity.ok(flightOrderingSystemService.buyTicketForTheBestFlight(desiredFlight));
    }
}