package com.michael.dynamodbdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.michael.dynamodbdemo.controller.model.SearchFlightsRequest;
import com.michael.dynamodbdemo.model.Flight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightOrderingSystemServiceTest {

    @Test
    void addFlight() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        System.out.println(objectMapper.writeValueAsString(Flight
                .builder()
                .planeId("1")
                .arrivalAirport("123")
                .arrivalTime(ZonedDateTime.now().minusHours(1))
                .departureAirport("London")
                .departureTime(ZonedDateTime.now())
                .number("123LA")
                .operator("ELAL")
                .ticketPrice(new BigDecimal(123))
                .build()));

        System.out.println(objectMapper.writeValueAsString(SearchFlightsRequest
                .builder()
                .arrivalAirport("123")
                .departureAirport("London")
                .build()));
    }
}