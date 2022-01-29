# dynamodb demo

1) Requirements
2) Data modeling: single table, streams, indexes, transactions, optimistic locking, TTL
3) Code
4) Deployment, docker/docker compose

Smart flight ordering system:

Rest api:

1. Create a airplane. - Method:POST - Endpoint: /plane - Payload:
2. Create a flight. - Method:POST - Endpoint: /flight - Payload:
3. Create a customer.- Method:POST - Endpoint: /passenger - Payload:
4. Find a most suitable flight by date/price/...,
5. Make an order. (Buy ticket)

