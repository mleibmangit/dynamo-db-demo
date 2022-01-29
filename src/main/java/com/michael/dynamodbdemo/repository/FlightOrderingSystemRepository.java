package com.michael.dynamodbdemo.repository;

import com.michael.dynamodbdemo.model.Flight;
import com.michael.dynamodbdemo.model.Passenger;
import com.michael.dynamodbdemo.model.Plane;
import com.michael.dynamodbdemo.repository.entity.FlightOrderingSystem;
import com.michael.dynamodbdemo.repository.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;

@Repository
public class FlightOrderingSystemRepository {

    private static final String TABLE_NAME = "FlightOrderingSystem";

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
                .gsi1pk(ObjectType.PLANE + "#" + flight.getPlaneId())
                .gsi1sk(ObjectType.PLANE + "#" + flight.getPlaneId())
                .build());
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
