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
    private final ZonedDateTime departureTime;
    private final ZonedDateTime  arrivingTime;
    private final BigDecimal maxPrice;

    @JsonCreator
    public SearchFlightsRequest(String departureAirport, String arrivingAirport, ZonedDateTime departureTime, ZonedDateTime arrivingTime, BigDecimal maxPrice) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivingAirport;
        this.departureTime = departureTime;
        this.arrivingTime = arrivingTime;
        this.maxPrice = maxPrice;
    }
}
