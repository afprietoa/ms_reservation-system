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

REST API allows performing the next functionalities

1. A customer can register in the hotel database, in this ENDPOINT it is validated that the id card number is an integer, and the first and last name contain data, and these can´t be null.

POST Method, request customer ENDPOINT example (http://localhost:8080/api/v1/customer ) [POST]

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

GET Method, request customer ENDPOINT example (http://localhost:8080/api/v1/customer/1 ) [GET])

Response Data

```java {.highlight .highlight-source-java .bg-black}
{
        "dni": 1,
        "firstName": "Pepito",
        "lastName": "Perez"
}
```

GET Method, request customer ENDPOINT example (http://localhost:8080/api/v1/customers ) [GET])

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

POST Method, request room ENDPOINT example (http://localhost:8080/api/v1/room ) [POST]

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

POST Method, request room ENDPOINT example (http://localhost:8080/api/v1/reservation/customer/1/room/1 ) [POST]

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

5. A customer can search available rooms by date and also can search all available rooms that be premium, o that be basic on a specific date.
