package com.michael.dynamodbdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.michael.dynamodbdemo.controller.model.SearchFlightsRequest;
import com.michael.dynamodbdemo.model.Flight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class FlightOrderingSystemServiceTest {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

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
                .arrivalAirport("Moscow")
                .departureAirport("London")
                .departureDate(ZonedDateTime.now(ZoneOffset.UTC).minusDays(5))
                .returnDate(ZonedDateTime.now().minusDays(2))
                .tripType(SearchFlightsRequest.TripType.ONE_WAY)
                .build()));


        System.out.println(dateTimeFormatter.format(ZonedDateTime.now(ZoneOffset.UTC).plusDays(5).truncatedTo(ChronoUnit.DAYS)));
        System.out.println(dateTimeFormatter.format(ZonedDateTime.now().plusDays(15).truncatedTo(ChronoUnit.DAYS)));

        System.out.println(dateTimeFormatter.format(ZonedDateTime.now().plusDays(5).truncatedTo(ChronoUnit.HOURS)));
        System.out.println(dateTimeFormatter.format(ZonedDateTime.now().plusDays(15).truncatedTo(ChronoUnit.HOURS)));

        DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println(dateTimeFormatterDate.format(ZonedDateTime.now().plusDays(15).truncatedTo(ChronoUnit.DAYS)));
    }
}