package com.michael.dynamodbdemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@ToString
@EqualsAndHashCode
@Getter
public class Flight {
    private final String number;
    private final String planeId;
    private final String operator;
    private final String departureAirport;
    private final String arrivalAirport;
    private final ZonedDateTime  departureTime;
    private final ZonedDateTime  arrivalTime;
    private final BigDecimal ticketPrice;

    @JsonCreator
    public Flight(String number, String planeId, String operator, String departureAirport, String arrivalAirport,
                  ZonedDateTime departureTime, ZonedDateTime arrivalTime, BigDecimal ticketPrice) {
        this.number = number;
        this.planeId = planeId;
        this.operator = operator;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ticketPrice = ticketPrice;
    }
}