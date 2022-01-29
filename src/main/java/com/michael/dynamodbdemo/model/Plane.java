package com.michael.dynamodbdemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Plane {
    private final String id;
    private final String type;
    private final int numberOfSeats;

    @JsonCreator
    public Plane(String id, String type, int numberOfSeats) {
        this.id = id;
        this.type = type;
        this.numberOfSeats = numberOfSeats;
    }
}