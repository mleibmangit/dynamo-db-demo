package com.michael.dynamodbdemo.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class CreatedObjectResponse {
    private final String id;
    private final ObjectType objectType;

    @JsonCreator
    public CreatedObjectResponse(String id, ObjectType objectType) {
        this.id = id;
        this.objectType = objectType;
    }

    public enum ObjectType {
        PLANE, PASSENGER, FLIGHT, ORDER
    }
}