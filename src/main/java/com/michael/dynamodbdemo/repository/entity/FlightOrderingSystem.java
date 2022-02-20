package com.michael.dynamodbdemo.repository.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@DynamoDbBean
public class FlightOrderingSystem {

    private String pk;
    private String sk;
    private String gsi1pk;
    private String gsi1sk;

    private String planeType;
    private int numberOfSeats;

    private String firstName;
    private String lastName;

    private String operator;
    private String departureAirport;
    private String arrivalAirport;
    private ZonedDateTime departureTime;
    private ZonedDateTime  arrivalTime;
    private BigDecimal ticketPrice;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSk() {
        return sk;
    }

    @DynamoDbAttribute("PlaneType")
    public String getPlaneType() {
        return planeType;
    }

    @DynamoDbAttribute("NumberOfSeats")
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @DynamoDbAttribute("FirstName")
    public String getFirstName() {
        return firstName;
    }

    @DynamoDbAttribute("LastName")
    public String getLastName() {
        return lastName;
    }

    @DynamoDbAttribute("Operator")
    public String getOperator() {
        return operator;
    }

    @DynamoDbAttribute("DepartureAirport")
    public String getDepartureAirport() {
        return departureAirport;
    }

    @DynamoDbAttribute("ArrivalAirport")
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @DynamoDbAttribute("DepartureDate")
    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    @DynamoDbAttribute("ArrivalTime")
    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    @DynamoDbAttribute("TicketPrice")
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "GSI1")
    @DynamoDbAttribute("GSI1-PK")
    public String getGsi1pk() {
        return gsi1pk;
    }

    @DynamoDbSecondarySortKey(indexNames = "GSI1")
    @DynamoDbAttribute("GSI1-SK")
    public String getGsi1sk() {
        return gsi1sk;
    }
}