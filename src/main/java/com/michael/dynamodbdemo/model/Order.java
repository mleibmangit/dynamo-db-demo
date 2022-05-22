package com.michael.dynamodbdemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Getter
public class Order {
    private final Passenger passenger;
    private final Flight flight;
    private final int numberOfTickets;
    private final BigDecimal price;

    @JsonCreator
    public Order(Passenger passenger, Flight flight, int numberOfTickets, BigDecimal price) {
        this.passenger = passenger;
        this.flight = flight;
        this.numberOfTickets = numberOfTickets;
        this.price = price;
    }
}