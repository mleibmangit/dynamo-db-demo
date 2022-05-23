package com.michael.dynamodbdemo.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class OrderRequest {
    private final String passengerUid;
    private final String flightNumber;

    @JsonCreator
    public OrderRequest(String passengerUid, String flightNumber) {
        this.passengerUid = passengerUid;
        this.flightNumber = flightNumber;
    }
}