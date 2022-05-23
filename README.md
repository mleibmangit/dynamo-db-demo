# dynamodb demo

1) Requirements
2) Data modeling: single table, streams, indexes, transactions, optimistic locking, TTL, ConditionExpression
3) Code
4) Deployment, docker/docker compose

Smart flight ordering system:

Rest api:

1. Create a airplane. - Method:POST - Endpoint: /addPlane - Payload:
2. Create a flight. - Method:POST - Endpoint: /addFlight - Payload:
3. Create a passenger.- Method:POST - Endpoint: /addPassenger - Payload:
4. Get all flights by arrival and destination airports and times.
5. Find a most suitable flight by date/price/...,
6. Make an order. (Buy ticket)

docker network create myNetwork
docker network connect myNetwork DynamoDbDemo
docker network connect myNetwork dynamodb-local

