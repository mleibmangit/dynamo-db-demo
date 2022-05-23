package com.michael.dynamodbdemo.repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ObjectType {
    PLANE, FLIGHT, PASSENGER, AIRPORT, DATE, ORDER;
}