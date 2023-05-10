# Reservation System Microservice

# Used Technologies
- Java version 11
- JUnit version 4.13.1
- Maven version 2.4.1
- Spring Boot version 2.7.11-SNAPSHOT

# Business problem
It's required to create a reservation REST API for ASHIR Hotel , the Api should allow customers to look for and to book room in the hotel. In addition, it must allow them to customer register in the hotel database. 

The System will have several entities, room, customer and reservation

A room is defined by:
- number
- room type
- base price

A reservation is definerd by:
- reserve date
- room
- customer
- reserve code
- total value

A customer is defined by:
- first name
- last name
- id card number
- address
- age
- e-mail

# Microservice proposal

# Class Diagram

![ClassDiagram1](https://github.com/afprietoa/ms_reservation-system/assets/68924563/36a924dd-d174-4fe4-bc7c-0062c4390fcb)

# ER Model

![ERDDiagram1](https://github.com/afprietoa/ms_reservation-system/assets/68924563/65c25ce4-f0b3-40c8-9b73-b3929e175e57)


REST API allows performing the next functionalities

1. A customer can register in the hotel database, in this ENDPOINT it is validated that the id card number is an integer, and the first and last name contain data, and these can´t be null.

Customer register, request customer ENDPOINT example (http://localhost:8080/api/v1/customer ) [POST]

Request Body

```json
{
        "dni": 1,
        "firstName": "Pepito",
        "lastName": "Perez",
        "address": "calle 123",
        "age": 18,
        "email": "pepito@gmail.com"
}

```

Response Data

```java {.highlight .highlight-source-java .bg-black}
{
        "dni": 1,
        "firstName": "Pepito",
        "lastName": "Perez",
        "address": "calle 123",
        "age": 18,
        "email": "pepito@gmail.com",
        "reservations": null
}

```

2. A specific customer must be recovered by id card number. In addition, it must be listed all customers. 

A customer with id card number 1, request customer ENDPOINT example (http://localhost:8080/api/v1/customer/1 ) [GET]

Response Data

```java {.highlight .highlight-source-java .bg-black}
{
        "dni": 1,
        "firstName": "Pepito",
        "lastName": "Perez"
}
```

All customers, request customer ENDPOINT example (http://localhost:8080/api/v1/customers ) [GET]

Response Data

```java {.highlight .highlight-source-java .bg-black}
{
        "dni": 1,
        "firstName": "Pepito",
        "lastName": "Perez"
},
{
        "dni": 2,
        "firstName": "Carlos",
        "lastName": "Rodriguez"
},
{
        "dni": 3,
        "firstName": "Alejandro",
        "lastName": "soto"
}
```


4. It must be register premium or basic type rooms.

Basic room register, request room ENDPOINT example (http://localhost:8080/api/v1/room ) [POST]

Request Body

```json
{
        "numberRoom": 1,
        "roomType": "basic",
        "price": 100.00
}

```

Response Data

```java {.highlight .highlight-source-java .bg-black}
{
        "numberRoom": 1,
        "roomType": "basic",
        "price": 100.00
}

```

3. A customer can reserve a room for a specific date, in this ENDPOINT it is validated that the customer is previously registered in the database and the date can't be null or the date is earlier than today.

Reservation register, request reservation ENDPOINT example (http://localhost:8080/api/v1/reservation/customer/1/room/1 ) [POST]

Request Body

```json
{
        "reserveDate": "2023-04-21",
        "totalValue": 100.00,
        "customer": null,
        "room": null

}

```

Response Data

```java {.highlight .highlight-source-java .bg-black}
{
        "reserveCode": 1;
        "reserveDate": "2023-04-21",
        "totalValue": 100.00,
        "customer": {
                 "dni": 1,
                "firstName": "Pepito",
                "lastName": "Perez",
                "address": "calle 123",
                "age": 18,
                "email": "pepito@gmail.com"
        },
        "room": { "numberRoom": 1, "roomType": "basic", "price": 100.00}
}
```
5. A customer can search available rooms by date and also can search all available rooms that be premium, o that be basic on a specific date.

Rooms with reserve date of 2023-04-21, request reservation ENDPOINT example (http://localhost:8080/api/v1/reservation/byDate/2023-04-21 ) [GET]

```java {.highlight .highlight-source-java .bg-black}
{
        "reserveCode": 1;
        "reserveDate": "2023-04-21",
        "totalValue": 100.00,
        "customer": {
                 "dni": 1,
                "firstName": "Pepito",
                "lastName": "Perez",
                "address": "calle 123",
                "age": 18,
                "email": "pepito@gmail.com"
        },
        "room": { "numberRoom": 1, "roomType": "basic", "price": 100.00}
},
{
        "reserveCode": 2;
        "reserveDate": "2023-04-21",
        "totalValue": 200.00,
        "customer": {
                 "dni": 1,
                "firstName": "Carlos",
                "lastName": "Rodri474",
                "age": 21,
                "email": "carlitos@gmail.com"
        },
        "room": { "numberRoom": 2, "roomType": "premium", "price": 200.00}
}
```
Rooms with reserve date of 2023-04-21 that are basic, , request reservation ENDPOINT example  (http://localhost:8080/api/v1/reservation/byDate/2023-04-21/byType/basic  ) GET

```java {.highlight .highlight-source-java .bg-black}
{
        "reserveCode": 1;
        "reserveDate": "2023-04-21",
        "totalValue": 100.00,
        "customer": {
                 "dni": 1,
                "firstName": "Pepito",
                "lastName": "Perez",
                "address": "calle 123",
                "age": 18,
                "email": "pepito@gmail.com"
        },
        "room": { "numberRoom": 1, "roomType": "basic", "price": 100.00}
}
```




