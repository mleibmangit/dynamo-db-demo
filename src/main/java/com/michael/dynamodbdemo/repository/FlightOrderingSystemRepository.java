package com.michael.dynamodbdemo.repository;

import com.michael.dynamodbdemo.controller.model.SearchFlightsRequest;
import com.michael.dynamodbdemo.model.Flight;
import com.michael.dynamodbdemo.model.Passenger;
import com.michael.dynamodbdemo.model.Plane;
import com.michael.dynamodbdemo.repository.entity.FlightOrderingSystem;
import com.michael.dynamodbdemo.repository.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class FlightOrderingSystemRepository {

    private static final String TABLE_NAME = "FlightOrderingSystem";
    private static final DateTimeFormatter dateTimeFormatterDate = DateTimeFormatter.ISO_LOCAL_DATE;

    private final DynamoDbEnhancedClient dynamoDbenhancedClient;
    private final DynamoDbTable<FlightOrderingSystem> flightOrderingSystemDynamoDbTable;

    @Autowired
    public FlightOrderingSystemRepository(DynamoDbEnhancedClient dynamoDbenhancedClient) {
        this.dynamoDbenhancedClient = dynamoDbenhancedClient;
        flightOrderingSystemDynamoDbTable
                = dynamoDbenhancedClient.table(TABLE_NAME, TableSchema.fromBean(FlightOrderingSystem.class));
    }

    public void addPlane(Plane plane) {

        flightOrderingSystemDynamoDbTable.putItem(FlightOrderingSystem
                .builder()
                .pk(ObjectType.PLANE + "#" + plane.getId())
                .sk(ObjectType.PLANE + "#" + plane.getId())
                .planeType(plane.getType())
                .numberOfSeats(plane.getNumberOfSeats())
                .build());
    }

    public void addPassenger(Passenger passenger) {

        flightOrderingSystemDynamoDbTable.putItem(FlightOrderingSystem
                .builder()
                .pk(ObjectType.PASSENGER + "#" + passenger.getUid())
                .sk(ObjectType.PASSENGER + "#" + passenger.getUid())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .build());
    }

    public void addFlight(Flight flight) {

        flightOrderingSystemDynamoDbTable.putItem(FlightOrderingSystem
                .builder()
                .pk(ObjectType.FLIGHT + "#" + flight.getNumber())
                .sk(ObjectType.FLIGHT + "#" + flight.getNumber())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .ticketPrice(flight.getTicketPrice())
                .gsi1pk(ObjectType.AIRPORT + "#" + flight.getDepartureAirport() + "#" + flight.getArrivalAirport())
                .gsi1sk(ObjectType.DATE + "#" + dateTimeFormatterDate.format(flight.getDepartureTime().truncatedTo(ChronoUnit.DAYS)))
                .build());
    }

    public List<Flight> searchFlights(SearchFlightsRequest searchFlightsRequest) {

        DynamoDbIndex<FlightOrderingSystem> gsi1 = flightOrderingSystemDynamoDbTable.index("GSI1");

        SdkIterable<Page<FlightOrderingSystem>> departingFlights = gsi1.query(QueryConditional.keyEqualTo(Key.builder()
                .partitionValue(ObjectType.AIRPORT + "#" + searchFlightsRequest.getDepartureAirport() + "#" + searchFlightsRequest.getArrivalAirport())
                .sortValue(prepareDateSearchExpression(searchFlightsRequest.getDepartureDate()))
                .build()));

        SdkIterable<Page<FlightOrderingSystem>> returningFlights = null;

        if (searchFlightsRequest.getTripType() == SearchFlightsRequest.TripType.ROUND_TRIP) {
            returningFlights = gsi1.query(QueryConditional.keyEqualTo(Key.builder()
                    .partitionValue(ObjectType.AIRPORT + "#" + searchFlightsRequest.getArrivalAirport() + "#" + searchFlightsRequest.getDepartureAirport())
                    .sortValue(prepareDateSearchExpression(searchFlightsRequest.getReturnDate()))
                    .build()));
        }

        Stream<Page<FlightOrderingSystem>> stream = returningFlights == null ? departingFlights.stream()
                : Stream.concat(departingFlights.stream(), returningFlights.stream());

        return stream
                .map(Page::items)
                .flatMap(Collection::stream)
                .map(fos -> Flight
                        .builder()
                        .arrivalAirport(fos.getArrivalAirport())
                        .departureAirport(fos.getDepartureAirport())
                        .arrivalTime(fos.getArrivalTime())
                        .departureTime(fos.getDepartureTime())
                        .number(fos.getPk())
                        .operator(fos.getOperator())
                        .planeId("TODO COMPLETE NAN")
                        .ticketPrice(fos.getTicketPrice())
                        .build())
                .collect(Collectors.toList());
    }

    private String prepareDateSearchExpression(ZonedDateTime flightTime) {
        return ObjectType.DATE + "#" + dateTimeFormatterDate.format(flightTime.truncatedTo(ChronoUnit.DAYS));
    }

    public void test() {
        DynamoDbTable<Music> musicTable = dynamoDbenhancedClient.table("Music", TableSchema.fromBean(Music.class));
        DescribeTableEnhancedResponse describeTableEnhancedResponse = musicTable.describeTable();
        Music david = musicTable.getItem(Key.builder()
                .partitionValue("David")
                .sortValue("Changes")
                .build());
        int i = 0;
    }
}
