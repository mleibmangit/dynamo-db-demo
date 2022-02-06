package com.michael.dynamodbdemo.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@ToString
@EqualsAndHashCode
@Getter
public class SearchFlightsRequest {
    private final String departureAirport;
    private final String arrivalAirport;
    private final ZonedDateTime departureDate;
    private final ZonedDateTime returnDate;
    private final TripType tripType;
    private final BigDecimal maxPrice;

    @JsonCreator
    public SearchFlightsRequest(String departureAirport, String arrivingAirport,
                                ZonedDateTime departureDate, ZonedDateTime returnDate,
                                TripType tripType, BigDecimal maxPrice) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivingAirport;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.tripType = tripType;
        this.maxPrice = maxPrice;
    }

    public enum TripType {
        ROUND_TRIP, ONE_WAY
    }
}
